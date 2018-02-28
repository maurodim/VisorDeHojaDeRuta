/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.objetosExportacion;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hernan
 */
public class ConeccionLocal implements Conectar{
private String archivoBase;
private String driver;
private String url;
private String usuario;
private String clave;

//private Connection cn;
    public ConeccionLocal() throws FileNotFoundException, IOException {
        File archivo=new File("Gestor//coneccion.txt");
        FileReader fR=new FileReader(archivo);
        BufferedReader bufferR=new BufferedReader(fR);
        archivoBase=bufferR.readLine();
        //archivoBase="C:\\Users\\Remoto1\\Documents\\NetBeansProjects\\VisorDeHojaDeRuta\\trabajos_deposito.mdb";
        System.out.println(archivoBase);
    }

    public ConeccionLocal(int idBase) {
        switch(idBase){
            
        }
    }
    
    @Override
    public Connection obtenerConeccion() {
        Connection cn = null;
        String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://201.235.253.65:3306/maurodim_sidercon";
		String usuario="mauro";  //"maurodim_mSider";
		String clave="mauro";
        	MysqlDataSource dataSource=new MysqlDataSource();
		try{
			//Class.forName(driver1).newInstance();
                    dataSource.setUser(usuario);
                    dataSource.setDatabaseName("sidercon");
                    dataSource.setPassword(clave);
                    dataSource.setServerName("localhost");//192.168.0.111
                    cn=dataSource.getConnection();
                 }catch(Exception ex){
                    //GuardarMovimientos gArch=new Archivador();
                String cod1=String.valueOf(ex);
                //gArch.registrarErrores(cod1, "", "");
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
            Logger.getLogger(ConeccionLocal.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
