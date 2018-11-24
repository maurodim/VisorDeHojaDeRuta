/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visordehojaderuta;

import interfacePedidos.objetosExportacion.ConeccionRemotaBdMysql;
import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Mensajeria.log;
import parametros.Parametrizar;
import parametros.TablasGenerales;
import proceso.chat.ChatSidercon;

/**
 *
 * @author MAURO DI
 */
public class VisorDeHojaDeRuta {

    /**
     * @param args the command line arguments
     */
    /*
    public static Map saldoCliente=new HashMap();
    public static ArrayList listaPedidos=new ArrayList();
    public static String fecha=null;
    */
    public static TablasGenerales tG;
    public static Connection conVV;
    public static String fecha;
    public static void main(String[] args) throws IOException {
        
        //Coneccion cnn=new Coneccion();
        File bases=new File("Seguimiento"); 
        if(!bases.isDirectory()){
            //JOptionPane.showMessageDialog(null,"INICIANDO CONFIGURACION Y CREACION DE LA BASE DE DATOS");
            bases.mkdirs();
            //ConeccionLocal.CrearDb();
            
        }
        Conectar conV=new ConeccionRemotaBdMysql();
        conVV=conV.obtenerConeccion();
         //log l=new log();
                DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        fecha=dia+"/"+mes+"/"+ano;
	//System.err.println(fecha);
        //fecha="23/12/2011";
        String fh=ano+"-"+mes+"-"+dia;
        SimpleDateFormat ff=new SimpleDateFormat("yyyy-mm-dd");
        Date fechaVal = null;
        try {
            fechaVal = ff.parse(fh);
        } catch (ParseException ex) {
            Logger.getLogger(VisorDeHojaDeRuta.class.getName()).log(Level.SEVERE, null, ex);
        }
        //l.validar(fechaVal);        
        tG=new TablasGenerales(1);
        //Parametrizar apr=new TablasGenerales();
        //apr.conectarEquipo(tG);
        ChatSidercon chat=new ChatSidercon();
        //chat.start();
        InicioVisorDeHojaDeRuta inicio=new InicioVisorDeHojaDeRuta();
        inicio.setExtendedState(InicioVisorDeHojaDeRuta.MAXIMIZED_BOTH);
        inicio.setTitle("GESTOR PARA VENDEDORES - "+tG.getOperador());
        inicio.setVisible(true);
        //LoginVisorHdr login=new LoginVisorHdr();
        

    }
} 
