/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.objetosExportacion;

import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hernan
 */
public class ConeccionRemotaAcces implements Conectar{

    @Override
    public Connection obtenerConeccion() {
        try {
            String database="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=\\\\Server\\bases Caja (SERVER)\\hdr.mdb";
            //String database="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=C:\\Users\\mauro di\\Documentos\\trabajos_deposito.mdb";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection(database, "", "");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionLocal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConeccionLocal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    }

    @Override
    public Boolean cerrarConeccion(Connection ac) {
        try {
            ac.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionRemotaAcces.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
