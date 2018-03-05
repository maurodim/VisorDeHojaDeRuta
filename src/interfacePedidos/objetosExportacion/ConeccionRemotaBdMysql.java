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
public class ConeccionRemotaBdMysql implements Conectar{

    @Override
    public Connection obtenerConeccion() {
		String driver="com.mysql.jdbc.Driver";
                /*
		String url="jdbc:mysql://201.235.253.65:3306/maurodim_sidercon";
		String usuario="maurodim_mSider";
		String clave="2428WEBmauro";
*/
                //String url="jdbc:mysql://192.168.0.111/sidercon";
		//String usuario="mauro";
		//String clave="mauro";
                
                String url="jdbc:mysql://192.168.0.111/sidercon";
                String usuario="hdr";
                String clave="daniel";
                
		Connection cn=null;
		try{
			Class.forName(driver).newInstance();
			cn=DriverManager.getConnection(url,usuario,clave);
			
		}catch(Exception ex){
			System.out.println("NO SE PUDO CONECTAR A LA BASE "+ex);
		}
		return cn;

    }

    @Override
    public Boolean cerrarConeccion(Connection ac) {
        try {
            ac.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionRemotaBdMysql.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
