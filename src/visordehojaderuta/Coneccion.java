/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visordehojaderuta;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class Coneccion {
    static Connection cn;

    public Connection getCn() {
        return cn;
    }
    
    public void cerrarCC(Connection cp) throws SQLException{
        cp.close();
    }
    public Coneccion(){
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://201.235.253.65:3306/maurodim_sidercon";
		String usuario="hdr";//"mauro";  //"maurodim_mSider";
		String clave="daniel";//"mauro";
        	MysqlDataSource dataSource=new MysqlDataSource();
		try{
			//Class.forName(driver1).newInstance();
                    dataSource.setUser(usuario);
                    dataSource.setDatabaseName("sidercon");
                    dataSource.setPassword(clave);
                    dataSource.setServerName("192.168.0.111");//192.168.0.
                    cn=dataSource.getConnection();
                 }catch(Exception ex){
                    //GuardarMovimientos gArch=new Archivador();
                String cod1=String.valueOf(ex);
                //gArch.registrarErrores(cod1, "", "");
			System.out.println("NO SE PUDO CONECTAR A LA BASE "+ex);
		}

    }
    public static Connection ObtenerConeccion(){		
		return cn;
	}
	public static void CerrarConneccion(Connection cb) throws SQLException{
		cb.close();
	}
}
