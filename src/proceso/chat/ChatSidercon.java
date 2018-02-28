/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import objetos.Mensajeria.Mensajes;
import parametros.Parametrizar;
import visordehojaderuta.InicioVisorDeHojaDeRuta;
import visordehojaderuta.MensajeriaSidercon;

/**
 *
 * @author hernan
 */
public class ChatSidercon extends Thread{
    static ArrayList listadoM=new ArrayList();
    static Mensajes sms=new Mensajes();
    public synchronized void run(){
    Timer timer=new Timer(100000,new ActionListener(){ 
    public void actionPerformed(ActionEvent e) 
    { 
       Parametrizar pMen=new Mensajes();
       listadoM=pMen.recibirMensaje();
       if(listadoM.size() > 0){
       MensajeriaSidercon men=new MensajeriaSidercon(listadoM);
       InicioVisorDeHojaDeRuta.jDesktopPane1.add(men);
       men.setVisible(true);
       men.toFront();
       }
       System.err.println("tamaño :"+listadoM.size());
        /*
        * BUCLE QUE LEE LA BASE PARA SABER SI HAY MENSAJES NUEVOS, LEE SI EL EQUIPO ESTA COMO DESTINATARIO Y SI ES ASI ABRE UNA JINTERNALFRAME CON EL MENSAJE
        * CORRESPONDIENTE
        * 
        */
          
          System.out.println("ESTA EN EL BUCLE");
          
     } 
}); 
        timer.start();

    }
}
