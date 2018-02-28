/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Mensajeria;

import interfacePedidos.objetosExportacion.ConeccionRemotaBdMysql;
import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import parametros.Parametrizar;
import visordehojaderuta.VisorDeHojaDeRuta;

/**
 *
 * @author Mauro Di
 * 
 * LA CLASE MENSAJES VA A GENERAR TODOS LOS MENSAJES INTERNOS DEL SISTEMA HDR, GESTOR DE VENDEDORES Y EDP EN FUTURO, VA A SER EL VINCULO COMUNICACIONAL DE LO SISTEMAS 
 * ENTRE SI, TENGO QUE GENERAR LA PLATAFORMA DE LAS TOMA DE DECISIONES PARA CADA OPERADOR, PUDIENDO ESTOS TOMAR UNA OPCION O NO Y ESTO AFECTAR A LOS DEMAS ACTORES
 * DE LOS SISTEMAS Y SUS OBJETOS
 * 
 */
public class Mensajes implements Parametrizar{
    private Integer numeroMensaje;
    private int equipoOrigen;
    private String descripcionEquipoOrigen;
    private Date fecha;
    private String texto;
    private Integer estadoMensaje;
    private Integer temaMensaje;
    private String descripcionTemaMensaje;
    private int equipoDestino;

    public String getDescripcionEquipoOrigen() {
        return descripcionEquipoOrigen;
    }

    public void setDescripcionEquipoOrigen(String descripcionEquipoOrigen) {
        this.descripcionEquipoOrigen = descripcionEquipoOrigen;
    }

    public String getDescripcionTemaMensaje() {
        return descripcionTemaMensaje;
    }

    public void setDescripcionTemaMensaje(String descripcionTemaMensaje) {
        this.descripcionTemaMensaje = descripcionTemaMensaje;
    }

    public Integer getNumeroMensaje() {
        return numeroMensaje;
    }

    public void setNumeroMensaje(Integer numeroMensaje) {
        this.numeroMensaje = numeroMensaje;
    }

    public int getEquipoOrigen() {
        return equipoOrigen;
    }

    public void setEquipoOrigen(int equipoOrigen) {
        this.equipoOrigen = equipoOrigen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getEstadoMensaje() {
        return estadoMensaje;
    }

    public void setEstadoMensaje(Integer estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }

    public Integer getTemaMensaje() {
        return temaMensaje;
    }

    public void setTemaMensaje(Integer temaMensaje) {
        this.temaMensaje = temaMensaje;
    }

    public int getEquipoDestino() {
        return equipoDestino;
    }

    public void setEquipoDestino(int equipoDestino) {
        this.equipoDestino = equipoDestino;
    }

    public Mensajes() {
        this.descripcionEquipoOrigen="";
        this.descripcionTemaMensaje="";
        this.equipoDestino=0;
        this.equipoOrigen=0;
        this.estadoMensaje=1;
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


        this.fecha=fechaVal;
        this.numeroMensaje=0;
        this.temaMensaje=0;
        this.texto="";
    }

    @Override
    public Boolean conectarEquipo(Object ob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean desconectarEquipo(Object ob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean marcarMensaje(Object mensaje, int estado) {
        try {
            Mensajes msn=(Mensajes)mensaje;
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            String sql="update mensajes set estado="+msn.estadoMensaje+" where numero="+msn.getNumeroMensaje();
            Statement st=cnn.createStatement();
            st.executeUpdate(sql);
            st.close();
            con.cerrarConeccion(cnn);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Mensajes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Boolean enviarMensaje(Object mensaje) {
        /*
         * LOS MENSAJES SON INDIVIDUALES (TEXTO - RECEPTOR) POR LO TANTO VOY A TENER QUE GENERAR TANTOS MENSAJES COMO RECEPTORES HAYA
         * SI NECESITO UN ENVIO GENERAL DEBO HACE UN ARRAY CON TODOS LOS RECEPTORES Y ENVIARLOS
         * 
         */
        try {
            Mensajes mms=(Mensajes)mensaje;
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            String sql="insert into mensajes (equipoOrigen,texto,tema,equipoDestino) values ("+mms.getEquipoOrigen()+",'"+mms.getTexto()+"',"+mms.getTemaMensaje()+","+mms.getEquipoDestino()+")";
            Statement st=cnn.createStatement();
            st.executeUpdate(sql);
            st.close();
            con.cerrarConeccion(cnn);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Mensajes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    @Override
    public ArrayList recibirMensaje() {
        try {
            ArrayList listadoM=new ArrayList();
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            int numeroEquipo=VisorDeHojaDeRuta.tG.getNumeroEquipo();
            Statement st=cnn.createStatement();
            String sql="select *,(select equipos.operador from equipos where equipos.numero=mensajes.equipoOrigen)as nombreRemitente,(select mensajestemas.descripcion from mensajestemas where mensajestemas.numero=mensajes.tema)as descrTemas from mensajes where equipoDestino ="+numeroEquipo+" and estado=1";
            System.out.println(sql);
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                Mensajes msn=new Mensajes();
                msn.setNumeroMensaje(rs.getInt("numero"));
                msn.setEquipoOrigen(rs.getInt("equipoOrigen"));
                msn.setDescripcionEquipoOrigen(rs.getString("nombreRemitente"));
                msn.setFecha(rs.getDate("fecha"));
                msn.setTexto(rs.getString("texto"));
                msn.setEstadoMensaje(rs.getInt("estado"));
                msn.setTemaMensaje(rs.getInt("tema"));
                msn.setDescripcionTemaMensaje(rs.getString("descrTemas"));
                listadoM.add(msn);
            }
            rs.close();
            st.close();
            con.cerrarConeccion(cnn);
            return listadoM;
        } catch (SQLException ex) {
            Logger.getLogger(Mensajes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
}
