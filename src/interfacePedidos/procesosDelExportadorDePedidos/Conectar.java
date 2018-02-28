/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.procesosDelExportadorDePedidos;

import java.sql.Connection;

/**
 *
 * @author hernan
 */
public interface Conectar {
    public Connection obtenerConeccion();//ENVIA LA SOLICITUD DE CONEXION Y OBTIENE EL STRING
    public Boolean cerrarConeccion(Connection ac);//CIERRA LA CONEXION Y DEVUELVE TRUE SI LO LOGRO
}
