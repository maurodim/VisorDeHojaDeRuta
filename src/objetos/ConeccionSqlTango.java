/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


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
 		driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
                //driver="com.mysql.jdbc.Driver";
        switch (base){
                    case 1:
                        url="jdbc:sqlserver://SERVERTANGO;databaseName=BU3";
                        //url="jdbc:mysql://localhost/tango";
                        break;
                    case 2:
                        url="jdbc:sqlserver://SERVERTANGO;databaseName=SIDERCON";
                        //url="jdbc:mysql://localhost/tango";
                        break;
                    case 3:
                        url="jdbc:sqlserver://SERVERTANGO;databaseName=SIDERCON_S_R_L";
                        //url="jdbc:mysql://localhost/tango";
                        break;
                    case 5:
                        url="jdbc:mysql://localhost/bu3";
                        break;
                    default:
                        url="jdbc:sqlserver://SERVERTANGO;databaseName=BU3";
                        //url="jdbc:mysql://localhost/tango";
                        break;
                }
		  //"jdbc:mysql://201.235.253.65:3306/maurodim_sidercon";
        /*
                  usuario="mauroS";
                  clave="1";
        */	
        usuario="Axoft";  //"maurodim_mSider";
		clave="Axoft";
          
		

    }
public static Connection ObtenerConeccion(int bb) throws ClassNotFoundException{
        int falloConeccion=0;
        Connection ccnn=null;
    try {
            Class.forName(ConeccionSqlTango.driver);
            String filename="\\\\Server\\bases Caja (SERVER)\\hdr.mdb";
            String databse="jdbc:odbc:Driver={Microsoft Access Driver(*.mdb)};DBQ=";
            databse+=filename.trim()+";DriverID=22;READONLY=true}";
            Configuracion conf=new Configuracion();
            Iterator icon=conf.leerConfiguraciones().listIterator();//SiderconCapaatos.listadoDeConfiguraciones.listIterator();
            //Configuracion conf=new Configuracion();
            switch (bb){
                case 1:
                    while(icon.hasNext()){
                        conf=(Configuracion)icon.next();
                        if(conf.getNombreConeccion().equals("Coneccion Tango BU")){
                            
                            ConeccionSqlTango.url=conf.getStringDeUrl();
                        }
                    }
                    break;
                case 2:
                    while(icon.hasNext()){
                        conf=(Configuracion)icon.next();
                        if(conf.getNombreConeccion().equals("Coneccion Tango SD")){
                            
                            ConeccionSqlTango.url=conf.getStringDeUrl();
                        }
                    }
                    break;
                case 5:
                    usuario="root";
                    clave="";
                    break;
                default:
                    while(icon.hasNext()){
                        conf=(Configuracion)icon.next();
                        if(conf.getNombreConeccion().equals("Coneccion Tango SD_SRL")){
                            
                            ConeccionSqlTango.url=conf.getStringDeUrl();
                        }
                    }
                    break;
            }
            String cadenn=ConeccionSqlTango.url+";user="+ConeccionSqlTango.usuario+";password="+ConeccionSqlTango.clave+";";
            //String cadenn=ConeccionSqlTango.url+";user="+ConeccionSqlTango.usuario+";password="+ConeccionSqlTango.clave+"";
            String cadenax="";
            
            try {
                ccnn = DriverManager.getConnection(cadenn); //this.url,this.usuario,this.clave);
                falloConeccion=0;
            } catch (SQLException ex) {
                falloConeccion=1;
                JOptionPane.showMessageDialog(null,"SE HA DETECTADO UN ERROR EN LA CONEXION AL SERVERTANGO\n CERRAR EL PROCESO Y VOLVER A INTENTARLO","CONEXION A SERVERTANGO ",JOptionPane.ERROR_MESSAGE);
                
                MysqlDataSource dataSource=new MysqlDataSource();
                
                //Class.forName(driver1).newInstance();
                dataSource.setUser(ConeccionSqlTango.usuario);
                dataSource.setDatabaseName("bu3");
                dataSource.setPassword(ConeccionSqlTango.clave);
                //dataSource.setServerName("192.168.0.111");
                dataSource.setServerName("localhost");
                try {
                    ccnn=dataSource.getConnection();
                    return ccnn;
                } catch (SQLException ex1) {
                    Logger.getLogger(ConeccionSqlTango.class.getName()).log(Level.SEVERE, null, ex1);
                }
                
            
                Logger.getLogger(ConeccionSqlTango.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ConeccionSqlTango.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ConeccionSqlTango.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ConeccionSqlTango.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ccnn;
}    
public static Boolean CerrarConeccion(Connection cn){
    try{
        cn.close();
        return true;
    }catch(Exception ex){
        
        return false;
    }
}
 private void emitirMensaje(){
        JOptionPane.showMessageDialog(null,"","CONEXION A SERVERTANGO ",JOptionPane.ERROR_MESSAGE);
    }
}
