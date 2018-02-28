/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.procesosDelExportadorDePedidos;

import java.util.ArrayList;

/**
 *
 * @author hernan
 */
public interface Ruteable {
    public Boolean Enviar(Object ruta);
    public Object Recibir();
    public Boolean Solicitar(Object ruta);
    public Boolean AperturaDeRuta(Object ruta);
    public Boolean Cerrar(Object ruta);
    public Boolean ReCerrarRuta(Object ruta);
    public ArrayList ListarRutas();
    public Integer VerificarCondicion(Object ruta,String fecha);
    public ArrayList ListarPredefinidas(String fecha);
}
