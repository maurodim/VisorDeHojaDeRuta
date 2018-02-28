/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visordehojaderuta;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author mauro di
 */
public class ConeccionSqlTango {
    static String driver;
    static String url;
    static String usuario;
    static String clave;
    public ConeccionSqlTango(int base) {
 	driver="com.mysql.jdbc.Driver";
	
        /*
        driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
                switch (base){
                    case 1:
                        url="jdbc:sqlserver://SERVERTANGO;databaseName=BU3";
                        break;
                    case 2:
                        url="jdbc:sqlserver://SERVERTANGO;databaseName=SIDERCON";
                        break;
                    default:
                        url="jdbc:sqlserver://SERVERTANGO;databaseName=SIDERCON_SRL";
                        break;
                }
		  //"jdbc:mysql://201.235.253.65:3306/maurodim_sidercon";
		usuario="Axoft";  //"maurodim_mSider";
		clave="Axoft";

	*/	

    }
public static Connection ObtenerConeccion(String bb) throws ClassNotFoundException{
      //String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://201.235.253.65:3306/maurodim_sidercon";
		String usuario="root";  //"maurodim_mSider";
		String clave="";
                Connection cn = null;
        	MysqlDataSource dataSource=new MysqlDataSource();
		try{
			//Class.forName(driver1).newInstance();
                    dataSource.setUser(usuario);
                    dataSource.setDatabaseName("bu3");
                    dataSource.setPassword(clave);
                    dataSource.setServerName("localhost");//192.168.0.
                    cn=dataSource.getConnection();
                 }catch(Exception ex){
                    //GuardarMovimientos gArch=new Archivador();
                String cod1=String.valueOf(ex);
                //gArch.registrarErrores(cod1, "", "");
			System.out.println("NO SE PUDO CONECTAR A LA BASE "+ex);
		}  
                return cn;
    /*
    driver="com.mysql.jdbc.Driver"; 
        usuario="Axoft";  //"maurodim_mSider";
        clave="Axoft";
        Class.forName(driver);
        String filename="\\\\Server\\bases Caja (SERVER)\\hdr.mdb";
        String databse="jdbc:odbc:Driver={Microsoft Access Driver(*.mdb)};DBQ=";
        databse+=filename.trim()+";DriverID=22;READONLY=true}";
        int base=0;
        if(bb.equals("BU"))base=1;
        if(bb.equals("SD"))base=2;
        if(bb.equals("SRL"))base=3;
        switch (base){
                    case 1:
                        
                                url="jdbc:sqlserver://SERVERTANGO;databaseName=BU3";
                         
                        break;
                    case 2:
                        
                                url="jdbc:sqlserver://SERVERTANGO;databaseName=SIDERCON";
                          
                        break;
                    default:
                        
                                url="jdbc:sqlserver://SERVERTANGO;databaseName=SIDERCON_S_R_L";
                          
                        break;
                }
        String cadenn=url+";user="+usuario+";password="+clave+";";
        String cadenax="";
        Connection ccnn=null;
        try {
            ccnn = DriverManager.getConnection(cadenn); //this.url,this.usuario,this.clave);
            //SiderconCapaatos.falloConecion=0;
        } catch (SQLException ex) {
            //SiderconCapaatos.falloConecion=1;
            JOptionPane.showMessageDialog(null,"SE HA DETECTADO UN ERROR EN LA CONEXION AL SERVERTANGO\n CERRAR EL PROCESO Y VOLVER A INTENTARLO","CONEXION A SERVERTANGO ",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ConeccionSqlTango.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ccnn;
        */
}    
public static Boolean CerrarConeccion(Connection cn){
    try{
        cn.close();
        return true;
    }catch(Exception ex){
        /**
        GuardarMovimientos gArch=new Archivador();
                String cod1=String.valueOf(ex);
                gArch.registrarErrores(cod1, "", "");
        */
        return false;
    }
}
 private void emitirMensaje(){
        JOptionPane.showMessageDialog(null,"","CONEXION A SERVERTANGO ",JOptionPane.ERROR_MESSAGE);
    }
}
