/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Mensajeria;

import java.util.ArrayList;

/**
 *
 * @author mauro
 */
public class CuerpoMail {
    private ArrayList listado;
    private String cuerpo;
    
    public String ArmarMensaje(ArrayList lista){
        listado=lista;
        cuerpo="<html><body>";
        
        
        
        cuerpo+="</body></html>";
        return cuerpo;
    }
}
