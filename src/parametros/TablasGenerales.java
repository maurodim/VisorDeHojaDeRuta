/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parametros;

import interfacePedidos.objetosExportacion.ConeccionRemotaBdMysql;
import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import visordehojaderuta.Coneccion;

/**
 *
 * @author hernan
 */
public class TablasGenerales implements Parametrizar{
   private int numeroEquipo;
   private String nombre;
   private String operador;
   private int estadoEquipo;
   private int numeroVendedor;

    public int getNumeroVendedor() {
        return numeroVendedor;
    }

    public void setNumeroVendedor(int numeroVendedor) {
        this.numeroVendedor = numeroVendedor;
    }

    public int getNumeroEquipo() {
        return numeroEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getOperador() {
        return operador;
    }

    public int getEstadoEquipo() {
        return estadoEquipo;
    }

    public TablasGenerales(int numeroEquipo) {
        this.estadoEquipo=4;
                this.numeroEquipo=numeroEquipo;
                this.nombre="Vendedor";
                this.operador="Mauro";
                this.numeroVendedor=27;
    }


    public TablasGenerales() throws IOException {
        try {
                    
            File archivo=new File("Gestor//coneccion.txt");
            int numeroEquipo=11;
            int valida=0;
            String destino = null;
                  
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            Statement st=cnn.createStatement();
            
            String sql="select * from vendedores ";
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                this.estadoEquipo=4;
                this.numeroEquipo=numeroEquipo;
                this.nombre=rs.getString("nombre");
                //this.operador=rs.getString("operador");
                this.numeroVendedor=rs.getInt("id_tango");
            }
            rs.close();
            st.close();
            con.cerrarConeccion(cnn);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TablasGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Boolean conectarEquipo(Object ob) {
        try {
            TablasGenerales tg=(TablasGenerales) ob;
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            Statement st=cnn.createStatement();
            String sql="insert into conecciones (equipo,tipo,operador) values ("+tg.numeroEquipo+",4,'"+tg.operador+"')";
            st.executeUpdate(sql);
            sql="update equipos set estado=4 where numero="+tg.numeroEquipo;
            st.executeUpdate(sql);
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TablasGenerales.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Boolean desconectarEquipo(Object ob) {
        try {
            TablasGenerales tg=(TablasGenerales) ob;
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cnn=con.obtenerConeccion();
            Statement st=cnn.createStatement();
            String sql="insert into conecciones (equipo,tipo,operador) values ("+tg.numeroEquipo+",5,'"+tg.operador+"')";
            st.executeUpdate(sql);
            sql="update equipos set estado=5 where numero ="+tg.numeroEquipo;
            st.executeUpdate(sql);
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TablasGenerales.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Boolean marcarMensaje(Object mensaje, int estado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean enviarMensaje(Object mensaje) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList recibirMensaje() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
   
}
