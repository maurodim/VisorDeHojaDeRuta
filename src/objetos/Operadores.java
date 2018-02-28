/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import visordehojaderuta.Coneccion;

/**
 *
 * @author Administrador
 */
public class Operadores {
    private int numero;
    private String nombre;
    private String clave;
    private Date horarioConeccion;
    private Date horarioCierre;
    private Date fecha;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(Date horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public Date getHorarioConeccion() {
        return horarioConeccion;
    }

    public void setHorarioConeccion(Date horarioConeccion) {
        this.horarioConeccion = horarioConeccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public Object login(String usuario,String clave) throws SQLException{
        //Boolean resultado=false;
        Coneccion con=new Coneccion();
        Connection cnn=con.ObtenerConeccion();
        Statement st=cnn.createStatement();
        String sql="select * from operadores where nombre like '"+usuario+"%'";
        st.execute(sql);
        ResultSet rs=st.getResultSet();
        while(rs.next()){
            this.nombre=rs.getString("nombre");
            this.numero=rs.getInt("numero");
        }
        return this;
    }
    
}
