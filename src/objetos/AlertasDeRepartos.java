/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfacesPrograma.Busquedas;
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
public class AlertasDeRepartos implements Busquedas{
    private int numeroDeAlertas;
    private String descripcionAlertas;

    public AlertasDeRepartos() {
        this.numeroDeAlertas=0;
        this.descripcionAlertas="";
    }

    public int getNumeroDeAlertas() {
        return numeroDeAlertas;
    }

    public void setNumeroDeAlertas(int numeroDeAlertas) {
        this.numeroDeAlertas = numeroDeAlertas;
    }

    public String getDescripcionAlertas() {
        return descripcionAlertas;
    }

    public void setDescripcionAlertas(String descripcionAlertas) {
        this.descripcionAlertas = descripcionAlertas;
    }

    @Override
    public ArrayList buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList filtrar(String numeroCliente, String nombreCliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar(String cliente) {
        try {
            ArrayList listado=new ArrayList();
            Coneccion con=new Coneccion();
            Connection cnn=con.ObtenerConeccion();
            String sql="select * from alertas order by numero";
            Statement st=cnn.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                AlertasDeRepartos alerta=new AlertasDeRepartos();
                alerta.setNumeroDeAlertas(rs.getInt("numero"));
                alerta.setDescripcionAlertas(rs.getString("descripcion"));
                listado.add(alerta);
                
            }
            rs.close();
            st.close();
            con.CerrarConneccion(cnn);
            return listado;
        } catch (SQLException ex) {
            Logger.getLogger(AlertasDeRepartos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    @Override
    public void marcarContactado(Integer item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificarDatosCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
