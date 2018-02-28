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
public class ZonasDeDistribucion implements Busquedas {
    private int numeroDeZona;
    private String descripcionDeZona;

    public ZonasDeDistribucion() {
        this.numeroDeZona =0;
        this.descripcionDeZona ="";
    }


    public int getNumeroDeZona() {
        return numeroDeZona;
    }

    public void setNumeroDeZona(int numeroDeZona) {
        this.numeroDeZona = numeroDeZona;
    }

    public String getDescripcionDeZona() {
        return descripcionDeZona;
    }

    public void setDescripcionDeZona(String descripcionDeZona) {
        this.descripcionDeZona = descripcionDeZona;
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
            String sql="select * from zonas order by numero";
            Statement st=cnn.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                ZonasDeDistribucion zona=new ZonasDeDistribucion();
                zona.setNumeroDeZona(rs.getInt("numero"));
                zona.setDescripcionDeZona(rs.getString("descripcion"));
                listado.add(zona);
            }
            rs.close();
            st.close();
            con.CerrarConneccion(cnn);
            return listado;
        } catch (SQLException ex) {
            Logger.getLogger(ZonasDeDistribucion.class.getName()).log(Level.SEVERE, null, ex);
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
