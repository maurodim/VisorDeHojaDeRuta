/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Mensajeria;

import interfacePedidos.objetosExportacion.ConeccionRemotaBdMysql;
import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
import interfacePedidos.procesosDelExportadorDePedidos.Ruteable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import visordehojaderuta.VisorDeHojaDeRuta;

/**
 *
 * @author hernan
 */
public class Rutas implements Ruteable{
    private int numeroDeRuta;
    private String descripcionRuta;
    private Date fechaReparto;
    private Boolean condicion;//SI LA RUTA ESTA ABIERTA O CERRADA
    private int numeroVehiculoAsignado;
    private String descripcionVehiculoAsignado;
    private Integer numeroHdrAsignada;
    private Integer numeroLpmAsignada;
    private int numeroVendedor;
    private Date fechaAlerta;//FECHA EN QUE SE PASA LA SOLICITUD DE APERTURA DE LA RUTA
    private String respuestaAlerta;//RESPUESTA DE LASOLICITUD DE APERTURA DE LA RUTA
    private Integer numeroAlerta;
    private Mensajes mensaje;

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getNumeroAlerta() {
        return numeroAlerta;
    }

    public void setNumeroAlerta(Integer numeroAlerta) {
        this.numeroAlerta = numeroAlerta;
    }

    public int getNumeroDeRuta() {
        return numeroDeRuta;
    }

    public void setNumeroDeRuta(int numeroDeRuta) {
        this.numeroDeRuta = numeroDeRuta;
    }

    public String getDescripcionRuta() {
        return descripcionRuta;
    }

    public void setDescripcionRuta(String descripcionRuta) {
        this.descripcionRuta = descripcionRuta;
    }

    public Date getFechaReparto() {
        return fechaReparto;
    }

    public void setFechaReparto(Date fechaReparto) {
        //Calendar c1=GregorianCalendar.getInstance();
        
        //c1.set(numeroDeRuta, numeroDeRuta, numeroDeRuta);
        this.fechaReparto = fechaReparto;
    }

    public Boolean getCondicion() {
        return condicion;
    }

    public void setCondicion(Boolean condicion) {
        this.condicion = condicion;
    }

    public int getNumeroVehiculoAsignado() {
        return numeroVehiculoAsignado;
    }

    public void setNumeroVehiculoAsignado(int numeroVehiculoAsignado) {
        this.numeroVehiculoAsignado = numeroVehiculoAsignado;
    }

    public String getDescripcionVehiculoAsignado() {
        return descripcionVehiculoAsignado;
    }

    public void setDescripcionVehiculoAsignado(String descripcionVehiculoAsignado) {
        this.descripcionVehiculoAsignado = descripcionVehiculoAsignado;
    }

    public Integer getNumeroHdrAsignada() {
        return numeroHdrAsignada;
    }

    public void setNumeroHdrAsignada(Integer numeroHdrAsignada) {
        this.numeroHdrAsignada = numeroHdrAsignada;
    }

    public Integer getNumeroLpmAsignada() {
        return numeroLpmAsignada;
    }

    public void setNumeroLpmAsignada(Integer numeroLpmAsignada) {
        this.numeroLpmAsignada = numeroLpmAsignada;
    }

    public int getNumeroVendedor() {
        return numeroVendedor;
    }

    public void setNumeroVendedor(int numeroVendedor) {
        this.numeroVendedor = numeroVendedor;
    }

    public Date getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(Date fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public String getRespuestaAlerta() {
        return respuestaAlerta;
    }

    public void setRespuestaAlerta(String respuestaAlerta) {
        this.respuestaAlerta = respuestaAlerta;
    }
    public int leerNumeroDeRuta(String detalle) throws SQLException{
        int numeroRuta=1;
        String sql="select zonas.numero from zonas where descripcion like '"+detalle+"'";
        Statement st=VisorDeHojaDeRuta.conVV.createStatement();
        st.execute(sql);
        ResultSet rs=st.getResultSet();
        while(rs.next()){
            numeroRuta=rs.getInt("numero");
        }
        st.close();
        return numeroRuta;
    }
    public Rutas() {
        this.condicion=true;
        this.descripcionRuta="SANTA FE";
        this.descripcionVehiculoAsignado="";
        DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        String fecha=dia+"/"+mes+"/"+ano;
	//System.err.println(fecha);
        //fecha="23/12/2011";
        String fh=ano+"-"+mes+"-"+dia;
        SimpleDateFormat ff=new SimpleDateFormat("yyyy-mm-dd");
        Date fechaVal = null;
        try {
            fechaVal = ff.parse(fh);
        } catch (ParseException ex) {
            Logger.getLogger(Rutas.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.fechaAlerta=fechaVal;
        this.fechaReparto=fechaVal;
        this.numeroDeRuta=1;
        this.numeroHdrAsignada=0;
        this.numeroLpmAsignada=0;
        this.numeroVehiculoAsignado=0;
        this.numeroVendedor=0;
        this.respuestaAlerta="";
        this.numeroAlerta=0;
        this.mensaje=new Mensajes();
    }

    @Override
    public Boolean Enviar(Object ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object Recibir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean Solicitar(Object ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean AperturaDeRuta(Object ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean Cerrar(Object ruta) {
        Boolean respuesta=false;
        try {
            Rutas rt=(Rutas)ruta;
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            String sql="insert into zonascondicion (fechaReparto,numeroZona,condicion,numeroMensaje) values ('"+rt.getFechaReparto()+"',"+rt.getNumeroDeRuta()+",2,"+rt.getNumeroAlerta()+")";
            Statement st=cnn.createStatement();
            st.executeUpdate(sql);
            st.close();
            respuesta=true;
        } catch (SQLException ex) {
            Logger.getLogger(Rutas.class.getName()).log(Level.SEVERE, null, ex);
            respuesta=false;
        }
        return respuesta;
    }

    @Override
    public Boolean ReCerrarRuta(Object ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList ListarRutas() {
        ArrayList listadoDeRutas=new ArrayList();
        try {
            
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            String sql="select * from zonas";
            Statement st=cnn.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                Rutas rt=new Rutas();
                rt.setNumeroDeRuta(rs.getInt("numero"));
                rt.setDescripcionRuta(rs.getString("descripcion"));
                
                /*
                if(this.VerificarCondicion(rt)==1){
                    rt.setCondicion(false);
                }else{
                    rt.setCondicion(true);
                }
                */ 
                listadoDeRutas.add(rt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rutas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoDeRutas;
        
    }

    @Override
    public Integer VerificarCondicion(Object ruta,String fecha) {
        Integer condicion=1;
        try {
            Rutas rtt=(Rutas)ruta;
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            Statement st=cnn.createStatement();
            String sql="select * from zonascondicion where numeroZona="+rtt.getNumeroDeRuta()+" and fechaReparto like '"+fecha+"%'";
            System.out.println("sentencia :"+sql);
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            try{
            while(rs.next()){
                condicion=rs.getInt("condicion");
            }
            }catch(SQLException ey){
                condicion=1;
               
            }
            rs.close();
            st.close();
            con.cerrarConeccion(cnn);
        } catch (SQLException ex) {
            Logger.getLogger(Rutas.class.getName()).log(Level.SEVERE, null, ex);
            condicion=1;
        }
        return condicion;
    }

    @Override
    public ArrayList ListarPredefinidas(String fecha) {
        ArrayList listadoPredefinidas = new ArrayList();
        try {
            ArrayList listadoRutas=new ArrayList();
            Rutas rtt;
            listadoRutas=this.ListarRutas();
            
            
            Iterator il=listadoRutas.listIterator();
            while(il.hasNext()){
                rtt=(Rutas)il.next();
                System.out.println("NUMERO "+rtt.getNumeroDeRuta()+" DESCRIP :"+rtt.getDescripcionRuta()+" tamaño "+listadoRutas.size());
            }
            String fech=null;
            String dia=null;
            String mes=null;
            String ano=null;
            dia=fecha.substring(0,2);
            mes=fecha.substring(3,5);
            ano=fecha.substring(6);
            fech=ano+"-"+mes+"-"+dia;
           String fechaConulta=ano+"-"+mes+"-"+dia;
            System.out.println("FECHA TRATADA "+fech);
            SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd",Locale.ROOT);
            Date fec=(Date)formato.parse(fech);
            GregorianCalendar cal=new GregorianCalendar();
            cal.setTime(fec);
            int diac=cal.get(Calendar.DAY_OF_WEEK);
            System.out.println("DIA DE LA SEMANA "+fec);
            SimpleDateFormat fr=new SimpleDateFormat("yyyy-MM-dd");
            Date fechh=(Date)fr.parse(fechaConulta);
            System.out.println("fecha formateada "+fechh);
            
            switch (diac){
                case 1:
                    break;
                case 2:
                    rtt=(Rutas)listadoRutas.get(0);
                    if(this.VerificarCondicion(rtt,fechaConulta)==1){
                    listadoPredefinidas.add(rtt);
                    }
                    break;
                case 3:
                    rtt=(Rutas)listadoRutas.get(0);
                    listadoPredefinidas.add(rtt);
                    rtt=(Rutas)listadoRutas.get(1);
                    rtt.setFechaReparto(fechh);
                    if(this.VerificarCondicion(rtt,fechaConulta)==1){
                    listadoPredefinidas.add(rtt);
                    }
                    break;
                case 4:
                    rtt=(Rutas)listadoRutas.get(0);
                    listadoPredefinidas.add(rtt);
                    rtt=(Rutas)listadoRutas.get(2);
                    listadoPredefinidas.add(rtt);
                    break;
                case 5:
                    rtt=(Rutas)listadoRutas.get(0);
                    listadoPredefinidas.add(rtt);
                    rtt=(Rutas)listadoRutas.get(1);
                    listadoPredefinidas.add(rtt);
                    break;
                case 6:
                    rtt=(Rutas)listadoRutas.get(0);
                    listadoPredefinidas.add(rtt);
                    rtt=(Rutas)listadoRutas.get(3);
                    listadoPredefinidas.add(rtt);
                    break;
                case 7:
                    rtt=(Rutas)listadoRutas.get(0);
                    listadoPredefinidas.add(rtt);
                    break;
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(Rutas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoPredefinidas;
    }
    
    
    
    
}
