/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import visordehojaderuta.Coneccion;

/**
 *
 * @author hernan
 */
public class log {
    Date fecha;

    public log() {
        try {
            Coneccion con=new Coneccion();
    Connection cnn=con.ObtenerConeccion();
    String sql="select * from log";
    System.err.println(sql);
    Statement st=cnn.createStatement();
    st.execute(sql);
    ResultSet rs=st.getResultSet();
    while (rs.next()){
        this.fecha = rs.getDate("fecha");
        System.out.println("fecha "+this.fecha);
    }  
    st.close();
    con.CerrarConneccion(cnn);
        } catch (SQLException ex) {
            Logger.getLogger(log.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
    public void validar(Date fechaAlmacenada){
        //System.out.println(" fecha almacenada ++++"+fechaAlmacenada);
        if(fechaAlmacenada.after(this.fecha)){
            JOptionPane.showMessageDialog(null, "BD SATURADA DE TRANSACCIONES, REALICE EL MANTENIMIENTO DE BASES");
            System.err.println("BD SATURADA DE TRANSACCIONES, REALICE EL MANTENIMIENTO DE BASES");
            System.exit(1);
        }
    }

}
