/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.clientes;


import interfacesPrograma.Busquedas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import visordehojaderuta.Coneccion;

/**
 *
 * @author Administrador
 */
public class ClientesTango implements Busquedas{
        private String codigoCliente;
        private String razonSocial;
        private Double saldo;
        private Integer condicionDePago;
        private Date fechaActualizacion;
        private String direccion;
        private String localidad;
        private String telefono;
        private String observaciones;
        private Boolean contactado;
        private Date fechaPedido;
        private String numeroPedido;
        private Date fechaListado;
        private Integer numeroListado;
        private Date fechaHdr;
        private Integer numeroHdr;
        private String numeroDeCuit;
        private String empresa;
        private Integer condicionDeVenta;
        private Integer listaDePrecios;
        private Double descuento;
        private String condicionIva;

    public String getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(String condicionIva) {
        this.condicionIva = condicionIva;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
        

    public Integer getCondicionDeVenta() {
        return condicionDeVenta;
    }

    public void setCondicionDeVenta(Integer condicionDeVenta) {
        this.condicionDeVenta = condicionDeVenta;
    }

    public Integer getListaDePrecios() {
        return listaDePrecios;
    }

    public void setListaDePrecios(Integer listaDePrecios) {
        this.listaDePrecios = listaDePrecios;
    }
        
        

    public String getNumeroDeCuit() {
        return numeroDeCuit;
    }

    public void setNumeroDeCuit(String numeroDeCuit) {
        this.numeroDeCuit = numeroDeCuit;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Boolean getContactado() {
        return contactado;
    }

    public void setContactado(Boolean contactado) {
        this.contactado = contactado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaHdr() {
        return fechaHdr;
    }

    public void setFechaHdr(Date fechaHdr) {
        this.fechaHdr = fechaHdr;
    }

    public Date getFechaListado() {
        return fechaListado;
    }

    public void setFechaListado(Date fechaListado) {
        this.fechaListado = fechaListado;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Integer getNumeroHdr() {
        return numeroHdr;
    }

    public void setNumeroHdr(Integer numeroHdr) {
        this.numeroHdr = numeroHdr;
    }

    public Integer getNumeroListado() {
        return numeroListado;
    }

    public void setNumeroListado(Integer numeroListado) {
        this.numeroListado = numeroListado;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
        

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
        


    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getCondicionDePago() {
        return condicionDePago;
    }

    public void setCondicionDePago(Integer condicionDePago) {
        this.condicionDePago = condicionDePago;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public void agregarNuevo(ClientesTango cli) throws SQLException{
        Connection cnn=Coneccion.ObtenerConeccion();
        Statement st=cnn.createStatement();
        String sql="insert into listcli (COD_CLIENT,RAZON_SOCI,DOMICILIO,LOCALIDAD,TELEFONO_1,TIPO_IVA,IDENTIFTRI,COND_VTA,NRO_LISTA,empresa) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getDireccion()+"','SANTA FE','"+cli.getTelefono()+"',"+cli.getCondicionIva()+",'"+cli.getNumeroDeCuit()+"',1,2,'"+cli.getEmpresa()+"')";
        st.executeUpdate(sql);
        st.close();
        Coneccion.CerrarConneccion(cnn);
    }
    public ArrayList listarPorVehiculo(int numeroVehiculo,String fecha) throws SQLException{
        ArrayList lista=new ArrayList();
        //String sql="select * from clientes where RAZON_SOCI like";
        String sql="select pedidos_carga1.COD_CLIENT,pedidos_carga1.RAZON_SOC,pedidos_carga1.listado,pedidos_carga1.LEYENDA_2,pedidos_carga1.NRO_PEDIDO,(select clientes.DOMICILIO from clientes where clientes.COD_CLIENT like pedidos_carga1.COD_CLIENT and clientes.RAZON_SOCI like pedidos_carga1.RAZON_SOC group by RAZON_SOC)as domicilio,(select clientes.TELEFONO_1 from clientes where clientes.COD_CLIENT like pedidos_carga1.COD_CLIENT and clientes.RAZON_SOCI like pedidos_carga1.RAZON_SOC group by RAZON_SOC)as telefono,(select clientes.LOCALIDAD from clientes where clientes.COD_CLIENT like pedidos_carga1.COD_CLIENT and clientes.RAZON_SOCI like pedidos_carga1.RAZON_SOC group by RAZON_SOC)as localidad,if((select contactos.contactado from contactos where contactos.numerocliente like pedidos_carga1.COD_CLIENT and contactos.numeroListado=pedidos_carga1.listado)=1,true,false)as contactado from pedidos_carga1 where vehiculo="+numeroVehiculo+" and entrega like '"+fecha+"%' group by RAZON_SOC";
                // and entregaConv like '"+fecha+"'";
        System.err.println(sql);
        Coneccion con=new Coneccion();
        Connection cnn=Coneccion.ObtenerConeccion();
        Statement st=cnn.createStatement();
        st.execute(sql);
        ResultSet rs=st.getResultSet();
        while(rs.next()){
             ClientesTango cli=new ClientesTango();
            cli.codigoCliente=rs.getString("COD_CLIENT");
            cli.razonSocial=rs.getString("RAZON_SOC");
            cli.numeroListado=rs.getInt("listado");
            cli.observaciones=rs.getString("LEYENDA_2");
            //this.fechaPedido=rs.getString("FEC_PEDIDO");
            cli.numeroPedido=rs.getString("NRO_PEDIDO");
            cli.direccion=rs.getString("domicilio");
            cli.telefono=rs.getString("telefono");
            cli.localidad=rs.getString("localidad");
            cli.contactado=rs.getBoolean("contactado");
            if(cli.contactado){
                
            }else{
            lista.add(cli);
            }
        }
        rs.close();
        st.close();
        Coneccion.CerrarConneccion(cnn);
        return lista;
    }

    @Override
    public ArrayList listar(String cliente) {
        try {
            ArrayList ped=new ArrayList();
            Coneccion con=new Coneccion();
            Connection cn=Coneccion.ObtenerConeccion();
            String sql="select * from listcli where RAZON_SOCI like '"+cliente+"%'";
            //String sql="select pedidos_carga1.COD_CLIENT,pedidos_carga1.RAZON_SOC,pedidos_carga1.NRO_PEDIDO,pedidos_carga1.numero,pedidos_carga1.LEYENDA_2 from pedidos_carga1 where RAZON_SOC like '"+cliente+"%' group by COD_CLIENT order by RAZON_SOC";
            Statement st=cn.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                
                ClientesTango cli=new ClientesTango();
                cli.setCodigoCliente(rs.getString("COD_CLIENT"));
                cli.setRazonSocial(rs.getString("RAZON_SOCI"));
                cli.setDireccion(rs.getString("DOMICILIO"));
                cli.setCondicionDeVenta(rs.getInt("COND_VTA"));
                cli.setListaDePrecios(rs.getInt("NRO_LISTA"));
                Double descuento=Double.parseDouble(rs.getString("PORC_DESC"));
                
                cli.setDescuento(descuento);
                cli.setNumeroDeCuit(rs.getString("IDENTIFTRI"));
                cli.setEmpresa(rs.getString("empresa"));
                cli.setCondicionIva(rs.getString("TIPO_IVA"));
                cli.setTelefono(rs.getString("TELEFONO_1"));
                cli.setLocalidad(rs.getString("LOCALIDAD"));
                //cli.setNumeroPedido(rs.getString(3));
                //cli.setObservaciones(rs.getString(5));
                System.out.println("CLIENTE "+cli.getRazonSocial() +"COMENTARIO "+cli.getCodigoCliente());
                ped.add(cli);
            }
            rs.close();
            st.close();
            Coneccion.CerrarConneccion(cn);
            return ped;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesTango.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }  
    

    @Override
    public void marcarContactado(Integer item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificarDatosCliente(Object cliente) {
        String datosContactados = null;
        String operador = null;
        String vehiculo = null;
        String entrega = null;
        try {
            ClientesTango cli=new ClientesTango();
            cli=(ClientesTango)cliente;
            Coneccion con=new Coneccion();
            Connection cnn=Coneccion.ObtenerConeccion();
            Statement st=cnn.createStatement();
            String sql="insert into contactos (numeroCliente,razonSocial,fechaLlamada,datoContactado,pedidoNumero,operadorNumero,actualizado,numeroListado,numeroVehiculo,fechaEntrega,contactado) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getFechaActualizacion()+"','"+datosContactados+"','"+cli.getNumeroPedido()+"',"+operador+",1,"+cli.getNumeroListado()+","+vehiculo+",'"+entrega+"')";
            st.executeUpdate(sql);
            st.close();
            Coneccion.CerrarConneccion(cnn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList filtrar(String numeroCliente, String nombreCliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
        
}
