/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.objetosExportacion;

import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Mensajeria.Rutas;
import objetos.Pedidos;
import visordehojaderuta.VisorDeHojaDeRuta;



/**
 *
 * @author mauro di
 */
public class PedidoParaExportar extends Pedidos{
    private String codigoArticulo;
    private String descripcionArticulo;
    private Double cantidadPedido;
    private Double cantidadPendiente;
    private Double cantidadEntregada;
    private String leyenda1;
    private String leyenda2;
    private String leyenda3;
    private Integer numeroListado;
    private Integer numeroHdr;
    private Double pesoItems;
    private Rutas ruta;
    private Integer numeroIdItem;

    public Integer getNumeroIdItem() {
        return numeroIdItem;
    }

    public void setNumeroIdItem(Integer numeroIdItem) {
        this.numeroIdItem = numeroIdItem;
    }

    public Rutas getRuta() {
        return ruta;
    }

    public void setRuta(Rutas ruta) {
        this.ruta = ruta;
    }

    public PedidoParaExportar() {
        this.cantidadEntregada=0.00;
        this.cantidadPedido=0.00;
        this.cantidadPendiente=0.00;
        this.codigoArticulo="";
        this.descripcionArticulo="";
        this.leyenda1="";
        this.leyenda2="";
        this.leyenda3="";
        this.numeroHdr=0;
        this.numeroListado=0;
        this.pesoItems=0.00;
        this.ruta=new Rutas();
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Double getCantidadPedido() {
        return cantidadPedido;
    }

    public void setCantidadPedido(Double cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public Double getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Double cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public Double getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(Double cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
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

    public Integer getNumeroListado() {
        return numeroListado;
    }

    public void setNumeroListado(Integer numeroListado) {
        this.numeroListado = numeroListado;
    }

    public Integer getNumeroHdr() {
        return numeroHdr;
    }

    public void setNumeroHdr(Integer numeroHdr) {
        this.numeroHdr = numeroHdr;
    }

    public Double getPesoItems() {
        return pesoItems;
    }

    public void setPesoItems(Double pesoItems) {
        this.pesoItems = pesoItems;
    }

    public ArrayList listarPed(String pedido) {
        ArrayList listadoDePedidos=new ArrayList();   
        try {
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            Statement st=cnn.createStatement();
            String sql="";
            
            sql="select pedidos_carga1.COD_ARTIC,pedidos_carga1.DESC_ARTIC,pedidos_carga1.CANT_PEDID,pedidos_carga1.CANT_FACT,pedidos_carga1.CANT_DESC,pedidos_carga1.LEYENDA_1,pedidos_carga1.LEYENDA_2,pedidos_carga1.LEYENDA_3,pedidos_carga1.entrega,pedidos_carga1.numero from pedidos_carga1 where listado =0 and reparto=1 and NRO_PEDIDO like '%"+pedido+"%' and hdr1=0";
            System.out.println("leer detalle "+sql);
            st.executeQuery(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                PedidoParaExportar pdExp=new PedidoParaExportar();
                pdExp.setCantidadPedido(rs.getDouble("CANT_PEDID"));
                pdExp.setCantidadPendiente(rs.getDouble("CANT_FACT"));
                pdExp.setCodigoArticulo(rs.getString("COD_ARTIC"));
                pdExp.setDescripcionArticulo(rs.getString("DESC_ARTIC"));
                pdExp.setFechaEntrega(rs.getString("entrega"));
                pdExp.setNumeroIdItem(rs.getInt("numero"));
                System.err.println(pdExp.getCodigoArticulo()+" "+pdExp.getCantidadPedido());
                listadoDePedidos.add(pdExp);
            }
            //return listadoDePedidos;
        } catch (SQLException ex) { 
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            //return null;
        }
        return listadoDePedidos;
        //return super.listarP();
    }
    @Override
    public Object ModificarCantidad(Object pedido, Double nuevaCantidad) {
                PedidoParaExportar ped=(PedidoParaExportar)pedido;
        try {           
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            Double cant=ped.getCantidadPedido() - nuevaCantidad;
            ped.setCantidadPedido(nuevaCantidad);
            ped.setCantidadPendiente(cant);
            String sql="update pedidos_carga1 set CANT_PEDID="+ped.getCantidadPedido()+",CANT_FACT="+ped.getCantidadPendiente()+" where numero="+ped.getNumeroIdItem();
            Statement st=cnn.createStatement();
            st.executeUpdate(sql);
            st.close();
            con.cerrarConeccion(cnn);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoParaExportar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ped;

    }
    @Override
    public Object ModificarFechaEnvioItem(Object pedido, String fechaNueva) {
        PedidoParaExportar ped=(PedidoParaExportar)pedido;
        try {
            

            String sql="update pedidos_carga1 set entrega ='"+fechaNueva+"' where numero="+ped.getNumeroIdItem();
            Statement st=VisorDeHojaDeRuta.conVV.createStatement();
            st.executeUpdate(sql);
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoParaExportar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ped;
        
    }

    @Override
    public Object ModificarCantidadPendiente(Object pedido, Double nuevaCantidadPendiente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public Object ModificarRutaDeEnvio(Object pedido, int numeroRuta) {
        PedidoParaExportar pd=(PedidoParaExportar)pedido;
        try {
            String sql="update pedidos_carga1 set zona ="+numeroRuta+" where numero ="+pd.getNumeroIdItem();
            Statement st=VisorDeHojaDeRuta.conVV.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoParaExportar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pd;
    }

    }

 