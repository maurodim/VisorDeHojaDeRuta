/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.objetosExportacion;

import interfacePedidos.procesosDelExportadorDePedidos.Clientable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class ClientesTangoVerificado implements Clientable{
    private Integer id;
    private String razonSocial;
    private String domicilio;
    private String localidad;
    private String eMail;
    private String telefono;
    private String codigoCliente;
    private Connection conSql;
    private String telefono2;
    private String eMail2;
    

    public Connection getConSql() {
        return conSql;
    }

    public void setConSql(Connection conSql) {
        this.conSql = conSql;
    }
    

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    
    public Integer getId() {
        return id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String geteMail() {
        return eMail;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }
    
    private Object CargarClienteTango(Connection bd, String nroPedido){
        try {
            
            
            String sql="select * from ar_clientes where COD_CLIENT like '"+nroPedido+"'";
            ClientesTangoVerificado cliente = null;
            //if(nroPedido.length() < 13)sql="select * from ar_pedidos where NRO_PEDIDO like ' "+nroPedido+"' order by ID_GVA03";
            System.out.println(sql);
                BufferedWriter bw = null;
                FileWriter fw = null;

                try {
                    String data = sql;
                    File file = new File("Seguimiento/logClientes.txt");
                    // Si el archivo no existe, se crea!
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    // flag true, indica adjuntar información al archivo.
                    fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);
                    bw.write(data);
                    bw.newLine();
                    System.out.println("información agregada!");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                                    //Cierra instancias de FileWriter y BufferedWriter
                        if (bw != null)
                            bw.close();
                        if (fw != null)
                            fw.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            
            
            Statement st=bd.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                cliente=new ClientesTangoVerificado();
                cliente.razonSocial=rs.getString("RAZON_SOCI");
                System.out.println("nombre cliente: "+cliente.razonSocial);
                cliente.domicilio=rs.getString("DOMICILIO");
                cliente.localidad=rs.getString("LOCALIDAD");
                cliente.eMail=rs.getString("E_MAIL");
                cliente.telefono=rs.getString("TELEFONO_MOVIL");
                cliente.codigoCliente=rs.getString("COD_CLIENT");
                cliente.conSql=bd;
            }
            
            rs.close();
            st.close();
            //Boolean cerrado=cnn.cerrarConeccion(cc);
            
            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesTangoVerificado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Object Cargar(Connection bdT, String codigoC) {
        return this.CargarClienteTango(bdT, codigoC);
    }

    @Override
    public Object Actualizar(Connection bdT, String codigoC) {
        return this.CargarClienteTango(bdT, codigoC);
    }
    
}
