/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;


import interfacePedidos.objetosExportacion.ConeccionRemotaBdMysql;
import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
import interfacePedidos.procesosDelExportadorDePedidos.ExportacionDePedidos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.Mensajeria.Corregible;
import objetos.Mensajeria.Rutas;
import visordehojaderuta.ConeccionSqlTango;

/**
 *
 * @author hernan
 */
public class Pedidos implements ExportacionDePedidos,Corregible{
    String numeroPedidos;
    private String razonSocial;
    private String empresa;
    private String fechaDePedido;
    private String codigoCliente;
    private int codigoVendedor;
    private String nombreVendedor;
    private int codigoDeposito;
    private int condicionDeVenta;
    private Double pesoPedido;
    private String fechaEntrega;
    private int numeroZona;
    private String descripcionZona;
    private String descripcionAlerta;
    private String leyenda1;
    private String leyenda2;
    private String leyenda3;
    private int numeroAlerta;
    private String fechaVieja;
    private Rutas ruta;
    ArrayList detallePedidos=new ArrayList();

    public Pedidos() {
        this.codigoCliente="";
        this.codigoDeposito=0;
        this.codigoVendedor=0;
        this.condicionDeVenta=2;
        this.descripcionAlerta="";
        this.descripcionZona="SANTA FE";
        this.detallePedidos=null;
        this.empresa="BU";
        this.fechaDePedido="";
        this.fechaEntrega="";
        this.fechaVieja="";
        this.leyenda1="";
        this.leyenda2="";
        this.leyenda3="";
        this.nombreVendedor="";
        this.numeroAlerta=0;
        this.numeroPedidos="";
        this.numeroZona=1;
        this.pesoPedido=0.00;
        this.razonSocial="";
        this.ruta=new Rutas();
    }

    public Rutas getRuta() {
        return ruta;
    }

    public void setRuta(Rutas ruta) {
        this.ruta = ruta;
    }

    public String getFechaVieja() {
        return fechaVieja;
    }

    public void setFechaVieja(String fechaVieja) {
        this.fechaVieja = fechaVieja;
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

    public ArrayList getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(ArrayList detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
    

    public String getDescripcionZona() {
        return descripcionZona;
    }

    public void setDescripcionZona(String descripcionZona) {
        this.descripcionZona = descripcionZona;
    }

    public String getDescripcionAlerta() {
        return descripcionAlerta;
    }

    public void setDescripcionAlerta(String descripcionAlerta) {
        this.descripcionAlerta = descripcionAlerta;
    }

    public String getNumeroPedidos() {
        return numeroPedidos;
    }

    public void setNumeroPedidos(String numeroPedidos) {
        this.numeroPedidos = numeroPedidos;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFechaDePedido() {
        return fechaDePedido;
    }

    public void setFechaDePedido(String fechaDePedido) {
        this.fechaDePedido = fechaDePedido;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public int getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(int codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public int getCondicionDeVenta() {
        return condicionDeVenta;
    }

    public void setCondicionDeVenta(int condicionDeVenta) {
        this.condicionDeVenta = condicionDeVenta;
    }

    public Double getPesoPedido() {
        return pesoPedido;
    }

    public void setPesoPedido(Double pesoPedido) {
        this.pesoPedido = pesoPedido;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getNumeroZona() {
        return numeroZona;
    }

    public void setNumeroZona(int numeroZona) {
        this.numeroZona = numeroZona;
    }

    public int getNumeroAlerta() {
        return numeroAlerta;
    }

    public void setNumeroAlerta(int numeroAlerta) {
        this.numeroAlerta = numeroAlerta;
    }
    public ArrayList listarP(){
                try {
            ArrayList listadoDePedidos=new ArrayList();
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            Statement st=cnn.createStatement();
            String sql="";
            if(this.codigoVendedor > 1){
                sql="select pedidos_carga1.NRO_PEDIDO,pedidos_carga1.LEYENDA_1,pedidos_carga1.LEYENDA_2,pedidos_carga1.LEYENDA_3,pedidos_carga1.COD_VENDED,pedidos_carga1.RAZON_SOC,pedidos_carga1.entrega,pedidos_carga1.zona,(select zonas.descripcion from zonas where zonas.numero=pedidos_carga1.zona)as zon from pedidos_carga1 where COD_VENDED ="+this.codigoVendedor+" and listado =0 and reparto=1 and hdr1=0 group by NRO_PEDIDO order by entregaConv";
            }else{
                sql="select pedidos_carga1.NRO_PEDIDO,pedidos_carga1.LEYENDA_1,pedidos_carga1.LEYENDA_2,pedidos_carga1.LEYENDA_3,pedidos_carga1.COD_VENDED,pedidos_carga1.RAZON_SOC,pedidos_carga1.entrega,pedidos_carga1.zona,(select zonas.descripcion from zonas where zonas.numero=pedidos_carga1.zona)as zon from pedidos_carga1 where listado =0 and reparto=1 and hdr1=0 group by NRO_PEDIDO order by entregaConv";
            }
            System.out.println("leer pedidos "+sql);
            st.executeQuery(sql);
            ResultSet rs=st.getResultSet();
            String zz;
            while(rs.next()){
                Pedidos pd=new Pedidos();
                pd.setNumeroPedidos(rs.getString("NRO_PEDIDO"));
                pd.setCodigoVendedor(rs.getInt("COD_VENDED"));
                pd.setRazonSocial(rs.getString("RAZON_SOC"));
                pd.setNumeroZona(rs.getInt("zona"));
                pd.setFechaEntrega(rs.getString("entrega"));
                pd.setFechaVieja(rs.getString("entrega"));
                pd.setLeyenda1(rs.getString("LEYENDA_1"));
                pd.setLeyenda2(rs.getString("LEYENDA_2"));
                pd.setLeyenda3(rs.getString("LEYENDA_3"));
                zz=rs.getString("zon");
                Rutas ruta=new Rutas();
                if(pd.getNumeroZona()==0){
                ruta.setNumeroDeRuta(pd.getNumeroZona());
                }else{
                    ruta.setNumeroDeRuta(pd.getNumeroZona());
                    ruta.setDescripcionRuta(pd.getDescripcionZona());
                }
                pd.setRuta(ruta);
                System.err.println(" ZZ "+zz);
                if(pd.getNumeroZona()==0 || pd.getNumeroZona()==1){
                    pd.setDescripcionZona("SANTA FE");
                }else{
                    pd.setDescripcionZona(zz);
                }
                listadoDePedidos.add(pd);
            }
            return listadoDePedidos;
        } catch (SQLException ex) { 
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    @Override
    public Boolean guardar(ArrayList list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList leer() {
        try {
            ArrayList listadoDePedidos=new ArrayList();
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            Statement st=cnn.createStatement();
            String sql="select pedidos_carga1.NRO_PEDIDO,pedidos_carga1.COD_VENDED,pedidos_carga1.RAZON_SOC from pedidos_carga1 where COD_VENDED ="+this.codigoVendedor+" and listado =0 group by NRO_PEDIDO";
            System.out.println("leer pedidos "+sql);
            st.executeQuery(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                Pedidos pd=new Pedidos();
                pd.setNumeroPedidos(rs.getString("NRO_PEDIDO"));
                pd.setCodigoVendedor(rs.getInt("COD_VENDED"));
                pd.setRazonSocial(rs.getString("RAZON_SOC"));
                listadoDePedidos.add(pd);
            }
            return listadoDePedidos;
        } catch (SQLException ex) { 
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Boolean modificar(Object ob) {
        try {
            Pedidos ped=new Pedidos();
            ped=(Pedidos)ob;
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            Statement st=cnn.createStatement();
            String sql="update pedidos_carga1 set entrega='"+ped.getFechaEntrega()+"', LEYENDA_1 ='"+ped.getLeyenda1()+"', LEYENDA_2 ='"+ped.getLeyenda2()+"',LEYENDA_3='"+ped.getLeyenda3()+"',zona ="+ped.getNumeroZona()+" where NRO_PEDIDO like '%"+ped.getNumeroPedidos()+"' and listado=0 and hdr1=0 and reparto=1 and entrega like '"+ped.getFechaVieja()+"%'";
            st.executeUpdate(sql);
            st.close();
            con.cerrarConeccion(cnn);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    @Override
    public Boolean enviar(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean enviarRemotaAcces(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void agregar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList validarEnvio(ArrayList listadoP) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean validarCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean actualizarDatosClientes(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notificar(String mensaje) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void marcarParaReparto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void marcarParaProceso() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fijarFecha() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean eliminarItems(ArrayList num) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public Boolean DejarPendiente(String numeroPedido, String fechaVieja, String fechaNueva, int nuevaRuta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean ConsultarCondicion(String numeroDePedido, String fechaVieja) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean AceptarAperturaRuta(String numeroDePedido, String fechaVieja, String fechaNueva) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean RechazarAperturaRuta(String numeroDePedido, String fechaVieja, String fechaNueva) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean ValidarCoincidencia(String numeroDePedido, String fechaNueva, int nuevaRuta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object ModificarCantidad(Object pedido, Double nuevaCantidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object ModificarFechaEnvioItem(Object pedido, String fechaNueva) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object ModificarCantidadPendiente(Object pedido, Double nuevaCantidadPendiente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object ModificarRutaDeEnvio(Object pedido, int numeroRuta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList validarBaseLocal(int vend, String emp, String fecc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public DefaultTableModel mostrarEnTabla(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList leerTango(int vendedor, String fecha, Connection bd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList leerDetalleTango(int vendedor, String fecha, Connection bd, String nroPedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validarEnviadoHdr(Object pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    

    

    
}
