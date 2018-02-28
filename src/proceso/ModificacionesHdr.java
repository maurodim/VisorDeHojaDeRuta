/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.DetalleHdr;
import visordehojaderuta.Coneccion;

/**
 *
 * @author Administrador
 */
public class ModificacionesHdr implements AbmHdr{
    private Coneccion con;
    private Connection cn;

    public ModificacionesHdr() {
        con=new Coneccion();
        cn=con.getCn();
    }
    
    @Override
    public void agregarItem(Object item,Integer numero,String comprobante) {
        try {
            DetalleHdr detalle=new DetalleHdr();
            detalle=(DetalleHdr) item;
            String sql="insert into detalle_hdr (cliente,empresa,comprobante,importe,observaciones,hdr) values ('"+detalle.getRazonSocial()+"','"+detalle.getEmpresa()+"','"+comprobante+"','"+detalle.getSaldo()+"','"+detalle.getObservaciones()+"',"+numero+")";
            Statement st=cn.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModificacionesHdr.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void anularHdr(Integer numeroHdr,String ax) {
        try {
            String sql="update hdr set estado=1,motivoAnulacion='"+ax+"',kmIniciO=0,kmFinal=0,horaInicio='00:00',horaFinal='00:00' where numero="+numeroHdr;
            Statement st=cn.createStatement();
            st.executeUpdate(sql);
            sql="update detalle_hdr set estadoHdr=1 where hdr="+numeroHdr;
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModificacionesHdr.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
