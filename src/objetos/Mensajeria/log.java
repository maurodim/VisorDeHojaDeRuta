package objetos.Mensajeria;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import interfacePedidos.objetosExportacion.ConeccionRemotaBdMysql;
import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hernan
 */
public class log {
    Date fecha;

    public log() {
        try {
            Conectar con=new ConeccionRemotaBdMysql();
    Connection cnn=con.obtenerConeccion();
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
    con.cerrarConeccion(cnn);
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
        }else{
        File dir=new File("\\\\SERVERTANGO\\c\\Resguardo");
        String[] ficheros=dir.list();
        Long tamao = null;
        byte total;
        Double resultado=null;
        Double totalC=0.00;
        int tot=0;
        for(int i=0;i < ficheros.length;i++){
            File arch=new File("\\\\SERVERTANGO\\c\\Resguardo\\"+ficheros[i]);
            tamao=arch.length();
            //total=total + tamao;
            System.err.println(arch+" tamaño carpeta :"+tamao+" numero "+i);
        
            resultado=arch.length()/1024.0;//a bytes
            resultado=resultado/1024.0;//a mega
            resultado=resultado/1024.0;//a gyga
            totalC=totalC + resultado;
            tot=i;
        }
        System.out.println("Tamaño carpeta :"+totalC);
        if (totalC > 70.0){
            System.err.println("EJECUTE LIMPIEZA URGENTE¡¡¡¡¡"+tot);
            for(int a=0;a <= tot;a++){
            File arc=new File("\\\\SERVERTANGO\\c\\Resguardo\\"+ficheros[a]);
            System.out.println(arc);
            arc.delete();
            }
            File dire=new File("\\\\SERVERTANGO\\c\\Mirror sidercon en server\\Sidercon en Server Mirroring\\Ventas");
            String[] ficha=dire.list();
            /*
            for (int aa=0;aa <= ficha.length;aa++){
                File vtas=new File("\\\\SERVERTANGO\\c\\Mirror sidercon en server\\Sidercon en Server Mirroring\\"+ficha[aa]);
                System.err.println(vtas);
            }
            */
        }
    
        }
    }

}
