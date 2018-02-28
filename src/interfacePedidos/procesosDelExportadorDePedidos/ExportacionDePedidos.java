/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.procesosDelExportadorDePedidos;

import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import visordehojaderuta.ConeccionSqlTango;

/**
 *
 * @author hernan
 */
public interface ExportacionDePedidos {
    
    public Boolean guardar(ArrayList list);//ENVIA EL OBJETO PEDIDO Y LO GUARDA EN LA BASE LOCAL - DEVUELVE TRUE SI FUE CORRECTA LA OPERACION
    public ArrayList leer();// LEE LA BD LOCAL
    //TIENE QUE ESTAR DENTRO DEL TIMER PARA QUE RECARGUE
    /*
     * PARA LA MODIFICACION DE PEDIDOS SE UTILIZA TAMBIEN ESTA INTERFACE CON EL OBJETO PEDIDO PARA EXPORTAR --
     * EN EL MISMO LEE LOS PEDIDOS PASADOS A REPARTO SIN LPM ASIGNADA Y DEVUELVE ESE LISTADO PARA TRABAJAR
     * SE UTILIZA EL OBJETO PEDIDOPARAEXPORTAR CON EL FIN DE ENCAPSULAR Y DELIMITAR LA INFORMACION QUE SE VA A PODER MODIFICAR
     * 
     */
    public ArrayList leerTango(int vendedor,String fecha, Connection bd);
    public Boolean modificar(Object ob);//MODIFICA EL OBJETO PEDIDO EN LA BD LOCAL - DEVUELVE TRUE SI FUE CORRECTO
    public Boolean enviar(ArrayList listado);// ENVIA A LA BD CENTRAL EL PEDIDO
    public Boolean enviarRemotaAcces(ArrayList listado);//ENVIA A BD ACCES DEL SERVER
    public void agregar();// AGREGA ITEMS EN EL PEDIDO
    public ArrayList validarEnvio(ArrayList listadoP);//VERIFICA QUE EL PEDIDO NO SE ENCUENTRE DUPLICADO EN BD REMOTA - SI ES FALSE NOTIFICAR
    public Boolean validarCliente(Object cliente);//PUEDE SER UTILIZADA PARA VERIFICAR CLIENTES.
    /*
     * UTILIZADA EN EL LOGIN DE VENDEDORE PARA VERIFICAR LA EXISTENCIA DEL MISMO, EN RELACION CON EL OBJETO VENDEDOR
     */
    public Boolean actualizarDatosClientes(Object cliente);//DE SER FALSE LA VALIDACION ENVIA LOS DATOS A LA TABLA DE MYSQL CON LOS DATOS 
    //CODIGO - RAZON SOCIAL - DIRECCION (LEYENDA_2 EN BD LOCAL) - 
    public void notificar(String mensaje);//DESPLIEGA EL JOPTIONPANE CON LA ACLARACION AL VENDEDOR DE QUE ESE PEDIDO YA HA SIDO ENVIADO Y O YA HA SIDO REMITIDO
    public void marcarParaReparto();//SI ESTA TILDADA ESTA OPCION LO REPLICA EN LA BD LOCAL
    public void marcarParaProceso();//SI ESTA TILDADA ESTA OPCION LO REPLICA EN LA BD LOCAL
    public void fijarFecha();//CARGA EL DATO EN EL PEDIDO (EN LA MATRIZ)
    public Boolean eliminarItems(ArrayList num);//ELIMINA EL ITEM DE LA BD - RETORNA TRUE  SI LA OPERACION SE COMPLETO
    public ArrayList validarBaseLocal(int vend,String emp,String fecc);//ELIMINA LOS CAMPOS DE LOS ITEMS YA REMITIDOS DE UN PEDIDO
    public DefaultTableModel mostrarEnTabla(ArrayList listado);
    public ArrayList leerDetalleTango(int vendedor,String fecha, Connection bd,String nroPedido);
    public String validarEnviadoHdr(Object pedido);
    
}
