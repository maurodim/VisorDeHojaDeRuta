/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import facturacion.clientes.ClientesTango;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import visordehojaderuta.Coneccion;

/**
 *
 * @author hernan
 */
public class Comprobantes {
    private int tipoComprobante;
    private String descripcionComprobante;
    private ClientesTango cliente;
    private String fechaComprobante;
    private ArrayList articulos=new ArrayList();
    private ArrayList observaciones=new ArrayList();
    private Double montoTotal;
    private Integer numero;
    private int reparto;
    private String entrega;
    private String vendedor;
    private String leyenda1;
    private String leyenda2;
    private String leyenda3;
    private int pagado;

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }
    
    public String getLeyenda1() {
        return leyenda1;
    }

    public void setLeyenda1(String leyenda1) {
        this.leyenda1 = leyenda1;
    }

    public String getLeyenda2() {
        return leyenda2;
    }

    public void setLeyenda2(String leyenda2) {
        this.leyenda2 = leyenda2;
    }

    public String getLeyenda3() {
        return leyenda3;
    }

    public void setLeyenda3(String leyenda3) {
        this.leyenda3 = leyenda3;
    }
    

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getReparto() {
        return reparto;
    }

    public void setReparto(int reparto) {
        this.reparto = reparto;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }
    

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    

    public int getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(int tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getDescripcionComprobante() {
        return descripcionComprobante;
    }

    public void setDescripcionComprobante(String descripcionComprobante) {
        this.descripcionComprobante = descripcionComprobante;
    }

    public ClientesTango getCliente() {
        return cliente;
    }

    public void setCliente(ClientesTango cliente) {
        this.cliente = cliente;
    }

    public String getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(String fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public ArrayList getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList articulos) {
        this.articulos = articulos;
    }

    public ArrayList getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(ArrayList observaciones) {
        this.observaciones = observaciones;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Comprobantes() {
    }
    public Integer guardarNuevo(int tipoComprobante) throws SQLException{
        Integer numero=0;
        Coneccion con=new Coneccion();
        Connection cnn=Coneccion.ObtenerConeccion();
        String sql=null;
        String sql1=null;
        switch (tipoComprobante){
            case 1:
                sql="select * from bkfacturasa order by numero desc limit 1";
                sql1="INSERT INTO bkfacturasa (numero) VALUES (NULL)";
                break;
            case 2:
                sql="select * from bkremitossd order by numero desc limit 1";
                sql1="INSERT INTO bkremitossd (numero) VALUES (NULL)";
                break;
            case 3:
                sql="select * from bkfacturasb order by numero desc limit 1";
                sql1="INSERT INTO bkfacturasb (numero) VALUES (NULL)";
                break;
            case 4:
                sql="select * from bkbu order by numero desc limit 1";
                sql1="INSERT INTO bkbu (numero) VALUES (NULL)";
                break;
            case 5:
                sql="select * from bkremitosbu order by numero desc limit 1";
                sql1="INSERT INTO bkremitosbu (numero) VALUES (NULL)";
                break;
            case 6:
                sql="select * from bkticket order by numero desc limit 1";
                sql1="INSERT INTO bkticket (numero) VALUES (NULL)";
                break;                
    }
        Statement st=cnn.createStatement();
        st.execute(sql);
        ResultSet rs=st.getResultSet();
        while(rs.next()){
            numero=rs.getInt("numero");
            
        }
        numero++;
        rs.close();
        st.executeUpdate(sql1);
        st.close();
        Coneccion.CerrarConneccion(cnn);
        return numero;
    }
    
    public Boolean guardarPedido(Comprobantes comp) throws SQLException{
        Boolean ok=true;
        Connection cnn=Coneccion.ObtenerConeccion();
        String sql=null;
        Statement st=cnn.createStatement();
        Articulos art=new Articulos();
        ArrayList listado=new ArrayList();
        listado=comp.getArticulos();
        Iterator al=listado.listIterator();
        while(al.hasNext()){
            art=(Articulos)al.next();
            String codigoArticulo=art.getCodigo();
            String descripcionArticulo=art.getDescripcionArticulo();
            Double cantidadArticulo=art.getCantidad();
            Double cantidadARemitir=art.getCantidad();
            Double precioArticulo=art.getPrecioUnitario();
            Double importeArticulo=art.getCantidad() * art.getPrecioUnitario();
            sql="insert into pedidos_carga1 (NRO_PEDIDO,TALON_PEDI,FEC_PEDIDO,COD_CLIENT,RAZON_SOC,BONIFIC,COND_VENTA,LISTA_PREC,LEYENDA_1,LEYENDA_2,LEYENDA_3,COD_ARTIC,DESC_ARTIC,CANT_PEDID,CANT_DESC,PRECIO,IMPORTE,entrega,reparto,smmd,pago) values ('MMD"+comp.getNumero()+"','"+comp.getCliente().getEmpresa().toUpperCase()+"','"+comp.getFechaComprobante()+"','"+comp.getCliente().getCodigoCliente()+"','"+comp.getCliente().getRazonSocial()+"',"+comp.getCliente().getDescuento()+","+comp.getCliente().getCondicionDeVenta()+","+comp.getCliente().getListaDePrecios()+",'"+comp.getLeyenda1()+"','"+comp.getLeyenda2()+"','"+comp.getLeyenda3()+"','"+codigoArticulo+"','"+descripcionArticulo+"',"+cantidadArticulo+","+cantidadARemitir+","+precioArticulo+","+importeArticulo+",'"+comp.getEntrega()+"',"+comp.getReparto()+",3,"+comp.getPagado()+")";
            System.out.println("para ver que falla "+sql);
            st.executeUpdate(sql);
        }
            if(comp.getPagado()==2){
                sql="insert into TABLA1 (COD_CLI,RAZON_SOC,FECHA,IMPORTE) VALUE ('"+comp.getCliente().getCodigoCliente()+"','"+comp.getCliente().getRazonSocial()+"','"+comp.getFechaComprobante()+"',"+comp.getMontoTotal()+")";
                System.err.println(sql);
                st.executeUpdate(sql);
            }else{
                sql="insert into TABLA1 (COD_CLI,RAZON_SOC,FECHA,IMPORTE) VALUE ('"+comp.getCliente().getCodigoCliente()+"','"+comp.getCliente().getRazonSocial()+"','"+comp.getFechaComprobante()+"',"+comp.getMontoTotal()+")";
                st.executeUpdate(sql);
                Double monto= comp.getMontoTotal() * -1;
                sql="insert into TABLA1 (COD_CLI,RAZON_SOC,FECHA,IMPORTE) VALUE ('"+comp.getCliente().getCodigoCliente()+"','"+comp.getCliente().getRazonSocial()+"','"+comp.getFechaComprobante()+"',"+monto+")";
                st.executeUpdate(sql);                
            }

        st.close();
        Coneccion.CerrarConneccion(cnn);
        return ok;
    }
    public Boolean cantidadRemitidas(Comprobantes comp) throws SQLException{
        Boolean ok=true;
        Articulos art=new Articulos();
        Connection cnn=Coneccion.ObtenerConeccion();
        Statement st=cnn.createStatement();
        String sql=null;
        Iterator al=comp.getArticulos().listIterator();
        while(al.hasNext()){
            art=(Articulos)al.next();
            sql="update pedidos_carga1 set CANT_DESC =(pedidos_carga1.CANT_DESC - "+art.getCantidad()+") where NRO_PEDIDO ='"+comp.getNumero()+"' and COD_ARTIC ='"+art.getCodigo()+"'";
            System.err.println("PARA LOS REMITOS "+sql);
            st.executeUpdate(sql);
            
        }
        return ok;
    }
}
