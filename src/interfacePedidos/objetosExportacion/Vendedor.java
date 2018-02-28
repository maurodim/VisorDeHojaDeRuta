/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.objetosExportacion;

import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
import interfacePedidos.procesosDelExportadorDePedidos.Editable;
import interfacePedidos.procesosDelExportadorDePedidos.ExportacionDePedidos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import objetos.ConeccionSqlTango;

/**
 *
 * @author hernan
 */
public class Vendedor implements ExportacionDePedidos,Editable {
    String nombre;
    Integer numero;
    Integer id_tango;

    public Integer getId_tango() {
        return id_tango;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public Boolean guardar(ArrayList list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

    @Override
    public Boolean modificar(Object ob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean enviar(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean enviarRemotaAcces(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void agregar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList validarEnvio(ArrayList listadoP) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   

    @Override
    public Boolean actualizarDatosClientes(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notificar(String mensaje) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void marcarParaReparto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void marcarParaProceso() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fijarFecha() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean eliminarItems(ArrayList num) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer validarVendedor(String nombre){
             try {
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cc=con.obtenerConeccion();
            Statement st=cc.createStatement();
            String sql="select * from vendedores where nombre like '"+nombre+"%'";
            Integer numero=0;
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                numero=rs.getInt("numero");
            }
            return numero;
        } catch (SQLException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Boolean validarCliente(Object cliente) {
             try {
             Vendedor vv=(Vendedor) cliente;
             Boolean aprov=false;
            Conectar con=new ConeccionRemotaBdMysql();
            Connection cc=con.obtenerConeccion();
            Statement st=cc.createStatement();
            String sql="select * from vendedores where nombre like '"+vv.getNombre()+"%'";
            System.err.println(sql);
            Integer numero=0;
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                numero=rs.getInt("numero");
                aprov=true;
            }
            return aprov;
        } catch (SQLException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList validarBaseLocal(int vend, String emp, String fecc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer agregar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listar() {
        ArrayList listado=new ArrayList();
        Conectar con=new ConeccionRemotaBdMysql();
            Connection cc=con.obtenerConeccion();
            Vendedor vend;
        try {
            Statement st=cc.createStatement();
            String sql="select * from vendedores order by nombre";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                vend=new Vendedor();
                vend.numero=rs.getInt("numero");
                vend.nombre=rs.getString("nombre");
                vend.id_tango=rs.getInt("id_tango");
                listado.add(vend);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
         return listado;   
    }

    @Override
    public void modificacion(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultComboBoxModel mostrarEnCombo(ArrayList listado) {
        DefaultComboBoxModel modelo=new DefaultComboBoxModel();
        Iterator it=listado.listIterator();
        Vendedor vv;
        while(it.hasNext()){
            vv=(Vendedor) it.next();
            modelo.addElement(vv.getId_tango()+" - "+vv.getNombre());
        }
        return modelo;
    }

    

    @Override
    public ArrayList leer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarEnTabla(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList leerTango(int vendedor, String fecha, Connection bd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList leerDetalleTango(int vendedor, String fecha, Connection bd, String nroPedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validarEnviadoHdr(Object pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

    
}
