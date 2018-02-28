/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parametros;

import java.util.ArrayList;

/**
 *
 * @author hernan
 */
public interface Parametrizar {
    public Boolean conectarEquipo(Object ob);
    public Boolean desconectarEquipo(Object ob);
    public Boolean marcarMensaje(Object mensaje,int estado);//SIRVE PARA LOS MENSAJES O RUTAS  EL OBJETO ES EL QUE SE LE PASE Y EL ESTADO ES CUAL VA A SER EL NUEVO
    public Boolean enviarMensaje(Object mensaje);
    public ArrayList recibirMensaje();
    
}
