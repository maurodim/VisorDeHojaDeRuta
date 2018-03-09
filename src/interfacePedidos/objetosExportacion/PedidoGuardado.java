/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.objetosExportacion;

import interfacePedidos.EditorDePedidos;
import interfacePedidos.procesosDelExportadorDePedidos.Conectar;
import interfacePedidos.procesosDelExportadorDePedidos.ExportacionDePedidos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import facturacion.clientes.ClientesTango;
import javax.swing.table.DefaultTableModel;
import objetos.Pedidos;
import objetos.PedidosParaReparto;
import visordehojaderuta.ConeccionSqlTango;


/**
 *
 * @author mauro di
 */
public class PedidoGuardado implements ExportacionDePedidos{
    private int ultimoRegistro=0;
    @Override
    public Boolean guardar(ArrayList ob) {
        try {
            PedidosParaReparto pedidos=new PedidosParaReparto();
            Boolean proces=true;
            Conectar cnn=new ConeccionLocal();
            Connection cc=cnn.obtenerConeccion();
            String sql="";
            String fechaEntrega="";
            Statement st=null;
            st=cc.createStatement();
            Iterator iob=ob.listIterator();
            int i=0;
            while(iob.hasNext()){
                try {
                    pedidos=(PedidosParaReparto)iob.next();
                    if(pedidos.getFechaEnvio()==null && pedidos.getFechaEnvio()==""){
                     fechaEntrega="00/00/0000"; 
                     System.err.println("entro en null "+fechaEntrega);
                    }else{
                     fechaEntrega=pedidos.getFechaEnvio();
                    }
                    //if(pedidos.getMarcadoComoAgregado()==0){
                    String validarComprobante=pedidos.getNumeroComprobante().substring(0,1);
                     validarComprobante.trim();
                     String descripcionArticulo="";
                    if((pedidos.getCantidadArticulo()==0) && (validarComprobante.equals("X"))){
                        if(pedidos.getDescripcionArticulo().length() > 30){
                            descripcionArticulo=pedidos.getDescripcionArticulo().substring(0,30);
                        }else{
                            descripcionArticulo=pedidos.getDescripcionArticulo();
                        }
                    }else{
                        descripcionArticulo=pedidos.getDescripcionArticulo();
                    }
                    sql="update pedidos_carga set reparto="+pedidos.getMarcadoParaReparto()+",proceso="+pedidos.getMarcadoParaProceso()+",DESC_ARTIC='"+descripcionArticulo+"',CANT_PEDID="+pedidos.getCantidadArticulo()+",CANT_FACT="+pedidos.getCantidadArticuloPendiente()+",CANT_DESC="+pedidos.getCantidadArticulosTotales()+",entrega='"+fechaEntrega+"',zona="+pedidos.getZonaAsignada()+",alerta="+pedidos.getAlertaAsignada()+" where numero="+pedidos.getiDPedido();
                    System.out.println(sql+" RANGO "+descripcionArticulo.length());
                    //st=cc.createStatement();
                    st.executeUpdate(sql);
                    i++;
                    EditorDePedidos.jProgressBar1.setValue(i);

                } catch (SQLException ex) {
                    Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
                    proces=false;
                }
                
            }
            st.close();
            cnn.cerrarConeccion(cc);
            return proces;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return null;
    }

    @Override
    public ArrayList leer() {
        try {
            ArrayList listadoP=new ArrayList();
            Conectar cnn = null;
            try {
                cnn = new ConeccionLocal();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection cc=cnn.obtenerConeccion();
            String sql="select * from pedidos_carga order by numero";
            Statement st=cc.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                PedidosParaReparto pedi=new PedidosParaReparto();
                pedi.setCodigoTangoDePedido(rs.getString(1));
                Double pedidas=rs.getDouble("CANT_PEDID");
                Double remitidas=rs.getDouble("CANT_DESC");
                Double facturadas=rs.getDouble("CANT_FACT");
                Double cantidadFinal=0.00;
                if(remitidas==0){
                    cantidadFinal=0.00;
                }else{
                    if(pedidas==remitidas){
                        cantidadFinal=pedidas;
                    }else{
                    cantidadFinal=remitidas;
                    }
                }
                if(facturadas > 0){
                    if(pedi.getCantidadArticuloPendiente()> 0 ){
                    cantidadFinal=remitidas - facturadas;
                    }
                }
                pedi.setNumeroVendedor(rs.getInt("COD_VENDED"));
                pedi.setCantidadArticulo(cantidadFinal);
                pedi.setCantidadArticuloPendiente(facturadas);
                pedi.setCantidadArticulosTotales(remitidas);
                pedi.setCodigoArticulo(rs.getString(26));
                pedi.setCodigoCliente(rs.getString(5));
                pedi.setCodigoDeposito(rs.getInt(10));
                pedi.setCondicionDeVenta(rs.getInt(8));
                pedi.setDescripcionArticulo(rs.getString(27)+rs.getString(28));
                pedi.setFechaPedidosTango(rs.getString(4));
                String fechEnv=rs.getString("entrega");
                System.out.println("vamos a ver la fecha de envio "+fechEnv);
                //String trim = fechEnv.trim();
                if(fechEnv == null || fechEnv.equals("0")){
                    //pedi.setFechaEnvio("");
                    System.err.println("FUE FECHA NULA");
                            
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
                    fechEnv=dia+"/"+mes+"/"+ano;
                    pedi.setFechaEnvio(fechEnv);
                    System.err.println(" FECHA INICIALIZADA EN "+dia+"/"+mes+"/"+ano);
                }else{
                    pedi.setFechaEnvio(fechEnv);
                    System.out.println("fecha envio "+pedi.getFechaEnvio());
                }
                System.out.println("fecha envio "+pedi.getFechaEnvio());
                pedi.setNumeroComprobante(rs.getString(19));
                pedi.setObservaciones(rs.getString(21));
                pedi.setObservaciones1(rs.getString(22));
                pedi.setObservaciones2(rs.getString(23));
                pedi.setRazonSocial(rs.getString(6));
                pedi.setiDPedido(rs.getInt("numero"));
                String empresa;
                switch(rs.getInt("TALON_PEDI")){
                    case 6:
                        empresa="BU";
                        break;
                    case 7:
                        empresa="SD";
                        break;
                    case 10:
                        empresa="SRL";
                        break;
                    default:
                        empresa="SRL";
                        break;
                }
                pedi.setEmpresa(empresa);
                pedi.setMarcadoParaProceso(rs.getInt("proceso"));
                pedi.setMarcadoParaReparto(rs.getInt("reparto"));
                pedi.setZonaAsignada(rs.getInt("zona"));
                pedi.setAlertaAsignada(rs.getInt("alerta"));
                listadoP.add(pedi);
                System.out.println(pedi.getRazonSocial());
                
            }
            
            rs.close();
            st.close();
            //Boolean cerrado=cnn.cerrarConeccion(cc);
            if(cnn.cerrarConeccion(cc)){
                //System.out.println("CONEXION CORRECTAMENTE CERRADA");
            }else{
                System.err.println("NO SE HA CERRADO LA CONEXION");
            }
            return listadoP;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Boolean modificar(Object ob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean enviar(ArrayList lista) {
        try {
            
            PedidosParaReparto ped=new PedidosParaReparto();
            Iterator il=lista.listIterator();
            ConeccionRemotaBdMysql conR=new ConeccionRemotaBdMysql();
            ArrayList numeroParaEliminar=new ArrayList();
            Connection cnn=conR.obtenerConeccion();
            Statement st=cnn.createStatement();
            String sql="";
            int i=0;
            while(il.hasNext()){
                try {
                    ped=(PedidosParaReparto)il.next();
                    
                    System.err.println(" proceso "+ped.getMarcadoParaProceso()+" reparto "+ped.getMarcadoParaReparto()+" pend "+ped.getCantidadArticuloPendiente());
                        if((ped.getMarcadoParaProceso()==1)||(ped.getMarcadoParaReparto()==1)||(ped.getCantidadArticuloPendiente()>0)){
                        Integer idPedido=0;
                        ultimoRegistro=0;
                            if(ped.getMarcadoParaProceso()==1){
                            System.out.println(" proceso "+ped.getMarcadoParaProceso());
                            if(enviarRemotaAc(ped)){
                                ultimoRegistro=ped.getiDPedido();
                                idPedido=ultimoRegistro;
                            }else{
                                
                            }
                        }
                        if(ped.getMarcadoParaReparto()==1){
                         
                        
                            
                        sql="insert into pedidos_carga1 (NRO_PEDIDO,FEC_PEDIDO,COD_CLIENT,RAZON_SOC,COND_VENTA,LEYENDA_1,LEYENDA_2,LEYENDA_3,COD_ARTIC,DESC_ARTIC,CANT_PEDID,CANT_FACT,CANT_DESC,entrega,reparto,peso,TALON_PEDI,numeroOriginal,zona,alerta,COD_VENDED,ID_GVA03) values ('"+ped.getCodigoTangoDePedido()+"','"+ped.getFechaPedidosTango()+"','"+ped.getCodigoCliente()+"','"+ped.getRazonSocial()+"',"+ped.getCondicionDeVenta()+",'"+ped.getObservaciones()+"','"+ped.getObservaciones1()+"','"+ped.getObservaciones2()+"','"+ped.getCodigoArticulo()+"','"+ped.getDescripcionArticulo()+"',"+ped.getCantidadArticulo()+","+ped.getCantidadArticuloPendiente()+","+ped.getCantidadArticulosTotales()+",'"+ped.getFechaEnvio()+"',"+ped.getMarcadoParaReparto()+",0.00"+",'"+ped.getEmpresa()+"',"+ultimoRegistro+","+ped.getZonaAsignada()+","+ped.getAlertaAsignada()+","+ped.getNumeroVendedor()+","+ped.getIdPedidosTango()+")";
                        System.out.println(sql);
                        st.executeUpdate(sql);
                        //System.out.println(sql);
                            idPedido=ped.getiDPedido();
                        
                        
                        }
                        if(idPedido > 0){
                            numeroParaEliminar.add(idPedido);
                        }

                        }
                    
                    i++;
                   // EditorDePedidos.jProgressBar1.setValue(i);
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                
            }
            //eliminarItems(numeroParaEliminar);            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void agregar() {
        try {
            Conectar cnn = null;
            try {
                cnn = new ConeccionLocal();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            }
       Connection cc=cnn.obtenerConeccion();
       String sql="insert into pedidos_carga (NRO_PEDIDO,TALON_PEDI,RAZON_SOC,DESC_ARTIC,CANT_PEDID,N_REMITO) values ('0000000000',5,'  ',' ',0.00,'200000000')";
       Statement st=cc.createStatement();
       st.executeUpdate(sql);
       st.close();
       cnn.cerrarConeccion(cc);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @Override
    public ArrayList validarEnvio(ArrayList listadoDeP) {
        try {
            ArrayList numerosRepetidos=new ArrayList();
            ConeccionRemotaBdMysql mSql=new ConeccionRemotaBdMysql();
            Connection con=mSql.obtenerConeccion();
            Statement st=con.createStatement();
            ResultSet rs=null;
            String sql="";
            PedidosParaReparto ped=new PedidosParaReparto();
            Iterator il=listadoDeP.listIterator();
            String fechasPedidos="";
            
            while(il.hasNext()){
                ped=(PedidosParaReparto)il.next();
                String descArtic="DUPLICACIÓN DE PEDIDOS \n";
                String numeroPed=ped.getCodigoTangoDePedido().substring(3);
                if(ped.getDescripcionArticulo().length()> 30){
                    descArtic=ped.getDescripcionArticulo().substring(0,30);
                }else{
                    descArtic=ped.getDescripcionArticulo();
                }
                sql="select pedidos_carga1.numero,pedidos_carga1.entrega from pedidos_carga1 where NRO_PEDIDO like '%"+numeroPed+"' and CANT_PEDID ="+ped.getCantidadArticulo()+" and TALON_PEDI ='"+ped.getEmpresa()+"' and ID_GVA03="+ped.getIdPedidosTango();
                
                st.execute(sql);
                rs=st.getResultSet();
                int h=0;
                while(rs.next()){
                    h++;
                    fechasPedidos+=" esta intentando duplicar el pedido del sr. "+ped.getRazonSocial()+" pasado ha sistema HDR para el día "+rs.getString("entrega")+"\n";
                }
                System.out.println(sql+" cant repetidos "+h);
                if(h > 0){
                    numerosRepetidos.add(ped.getiDPedido());
                }
                
            }
                if(numerosRepetidos.size()>0){
                    this.notificar(fechasPedidos);
                }
                rs.close();
                st.close();
                mSql.cerrarConeccion(con);
                return numerosRepetidos;
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void notificar(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje,"Editor de Pedidos - SIDERCON",JOptionPane.ERROR_MESSAGE);
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
    public Boolean eliminarItems(ArrayList numeros) {
        try {
            Conectar cnn = null;
            try {
                cnn = new ConeccionLocal();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection cc=cnn.obtenerConeccion();
            String sql="";
            Statement st=cc.createStatement();
            Iterator numL=numeros.listIterator();
            Integer numeroP=0;
            while(numL.hasNext()){
                numeroP=(Integer)numL.next();
                sql="delete * from pedidos_carga where numero="+numeroP;
                st.executeUpdate(sql);
            }
            st.close();
            cnn.cerrarConeccion(cc);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList validarBaseLocal(int vend,String emp,String fecc) {
        ArrayList listadoDeEliminados=new ArrayList();
        try {
            
            ConeccionSqlTango cnn = null;
            //cnn = new ConeccionLocal();
            cnn=new ConeccionSqlTango(5);
            Connection cc=ConeccionSqlTango.ObtenerConeccion(String.valueOf(5));
            //String sql="select * from AR_PEDIDOS where COD_VENDED="+vend+" and FECHA_PEDI LIKE '"+fecc+"' and cantidad_facturada > 0 order by ID_GVA03";
            String sql="select * from AR_PEDIDOS where COD_VENDED="+vend+" and FECHA_PEDI LIKE '"+fecc+"' and cantidad_facturada > 0 order by numero";
            Statement st=cc.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                
                String validarComprobante=rs.getString("N_COMP").substring(0,1);
                validarComprobante.trim();
                System.out.println(validarComprobante);
                Double pedidas=rs.getDouble("CANT_PEDID");
                Double remitidas=rs.getDouble("cantidad_descargada");
                Double cantidadFinal=0.00;
                if(remitidas==0){
                    cantidadFinal=0.00;
                }else{
                    if(pedidas==remitidas){
                        cantidadFinal=pedidas;
                    }else{
                    cantidadFinal=remitidas;
                    }
                }

if((cantidadFinal ==0) && (validarComprobante.equals("X"))){
    //listadoDeEliminados.add(rs.getInt("ID_GVA03"));
}else{
    listadoDeEliminados.add(rs.getInt("ID_GVA03"));
}
            }
            rs.close();
            st.close();
            //cnn.cerrarConeccion(cc);
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoDeEliminados;
    }

    @Override
    public Boolean validarCliente(Object cliente) {
        try {
            ClientesTango cli=new ClientesTango();
            cli=(ClientesTango)cliente;
            String sql="select clientes.COD_CLIENT from clientes where COD_CLIENT like '%"+cli.getCodigoCliente()+"' and RAZON_SOCI like '"+cli.getRazonSocial()+"%'";
            ConeccionRemotaBdMysql conR=new ConeccionRemotaBdMysql();
            Connection cnn=conR.obtenerConeccion();
            Statement st=cnn.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            int a=0;
            while(rs.next()){
                a++;
            }
            rs.close();
            st.close();
            conR.cerrarConeccion(cnn);
            if(a >0){
            return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("ERROR EN LA CONEXION A MYSQL "+ex);
            return true;
        }
        
    }

    @Override
    public Boolean actualizarDatosClientes(Object cliente) {
        try {
            ClientesTango cli=new ClientesTango();
            cli=(ClientesTango)cliente;
            ConeccionRemotaBdMysql conR=new ConeccionRemotaBdMysql();
            Connection cnn=conR.obtenerConeccion();
            String domi="";
            if(cli.getDireccion().length()> 30){
                domi=cli.getDireccion().substring(0,30);
            }else{
                domi=cli.getDireccion();
            }
            String sql="insert into clientes (COD_CLIENT,RAZON_SOCI,DOMICILIO) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+domi+"')";
            System.out.println(sql);
            
            Statement st=cnn.createStatement();
            st.executeUpdate(sql);
            
            st.close();
            conR.cerrarConeccion(cnn);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }

    @Override
    public Boolean enviarRemotaAcces(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Boolean enviarRemotaAc(Object listado){
        try {
            PedidosParaReparto ped=new PedidosParaReparto();
    //Iterator il=lista.listIterator();
    ConeccionRemotaAcces conR=new ConeccionRemotaAcces();
    Connection cnn=conR.obtenerConeccion();
    Statement st=cnn.createStatement();
    String sql="";
    int i=0;
     try {
            ped=(PedidosParaReparto)listado;
            String fechaOriginal="";
            String dia=ped.getFechaPedidosTango().substring(8,10);
            String mes=ped.getFechaPedidosTango().substring(5,7);
            String ano=ped.getFechaPedidosTango().substring(0,4);
            fechaOriginal=dia+"/"+mes+"/"+ano;
            int empresa=0;
            String emp=ped.getEmpresa();
            if(emp.equals("BU"))empresa=5;
            if(emp.equals("SD"))empresa=6;
            if(emp.equals("SRL"))empresa=10;
            if(empresa==0)empresa=6;
            /*
            switch (emp){
                case "BU":
                    empresa=5;
                    break;
                case "SD":
                    empresa=6;
                    break;
                case "SRL":
                    empresa=10;
                    break;
                default:
                    empresa=6;
                    break;
            }
*/
            System.out.println("FECHA "+fechaOriginal+" original "+ped.getFechaPedidosTango());
                sql="insert into pedidos_carga (NRO_PEDIDO,FEC_PEDIDO,COD_CLIENT,RAZON_SOC,COND_VENTA,LEYENDA_1,LEYENDA_2,LEYENDA_3,COD_ARTIC,DESC_ARTIC,CANT_PEDID,entrega,reparto,proceso,orden_num,condicion,TALON_PEDI,actualizacionRegistro) values ('"+ped.getCodigoTangoDePedido()+"','"+ped.getFechaPedidosTango()+"','"+ped.getCodigoCliente()+"','"+ped.getRazonSocial()+"',"+ped.getCondicionDeVenta()+",'"+ped.getObservaciones()+"','"+ped.getObservaciones1()+"','"+ped.getObservaciones2()+"','"+ped.getCodigoArticulo()+"','"+ped.getDescripcionArticulo()+"',"+ped.getCantidadArticulo()+",'"+ped.getFechaEnvio()+"',"+ped.getMarcadoParaReparto()+","+ped.getMarcadoParaProceso()+","+ped.getNumeroDeProceso()+",0,"+empresa+",1)";
               System.out.println(sql);
                st.executeUpdate(sql);
               

        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("NO ENTRO");
            return false;
        }
     sql="select pedidos_carga.numero from pedidos_carga where NRO_PEDIDO ='"+ped.getCodigoTangoDePedido()+"' order by numero";
     st.execute(sql);
     ResultSet rs=st.getResultSet();
     while(rs.next()){
         ultimoRegistro=rs.getInt(1);
         System.err.println("ULTIMO REGISTRO EN ACCESS "+ultimoRegistro);
     }
      st.close();
      conR.cerrarConeccion(cnn);
    
    return true;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    private Boolean validarFacturado(PedidosParaReparto ped){
    PedidosParaReparto pediR=ped;
    //Iterator itLP=listadoDePedidos.listIterator();
    int bb=1;
    Boolean respuesta=false;
    ConeccionSqlTango tango=new ConeccionSqlTango(1);
    //Connection bu=(Connection)ConeccionSqlTango.ObtenerConeccion(bb);
    tango=new ConeccionSqlTango(2);
    //Connection sd=ConeccionSqlTango.ObtenerConeccion(2);
    tango=new ConeccionSqlTango(3);
    //Connection srl=ConeccionSqlTango.ObtenerConeccion(3);
    //sqlBu=(Connection) ConeccionSqlTango.ObtenerConeccion(1);
    //sqlSd=(Connection) ConeccionSqlTango.ObtenerConeccion(2);
    //sqlSdSrl=(Connection) ConeccionSqlTango.ObtenerConeccion(3);
    String sql;
    String empresa=null;
    Connection conTango = null;
    Statement xt;
    ResultSet rs;
    
        
        
            empresa=pediR.getEmpresa();
            try {
                conTango=ConeccionSqlTango.ObtenerConeccion(empresa);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EditorDePedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        try {
            
            sql="select GVA03.CAN_EQUI_V,GVA03.CANT_A_DES,GVA03.CANT_PEDID,GVA03.CANT_PEN_F,GVA03.COD_ARTICU from GVA03 where NRO_PEDIDO like '%"+pediR.getCodigoTangoDePedido()+"' and COD_ARTICU ='"+pediR.getCodigoArticulo()+"' order by GVA03.CANT_A_DES";
            xt=conTango.createStatement();
            rs=xt.executeQuery(sql);
            Double equivalente=0.00;
            Double cantidadFacturada=0.00;
            Double cantidadPedida=0.00;
            while(rs.next()){
                equivalente=rs.getDouble("CAN_EQUI_V");
                cantidadPedida=rs.getDouble("CANT_PEDID");
                cantidadFacturada=rs.getDouble("CANT_PEN_F");
                if(cantidadFacturada < cantidadPedida || cantidadFacturada == cantidadPedida){
                    //cantidadFacturada=cantidadPedida - cantidadFacturada;
                    cantidadFacturada=cantidadFacturada / equivalente;
                    cantidadPedida=cantidadPedida / equivalente;
                    
                        respuesta=true;
                        if(cantidadFacturada==0.00){
                            
                        }else{
                            cantidadPedida=cantidadPedida - cantidadFacturada;
                        pediR.setCantidadArticulo(cantidadPedida);
                        pediR.setCantidadArticuloPendiente(cantidadPedida);
                        //pediR.setCantidadOriginal(cantidadPedida);
                        }
                }else{
                    JOptionPane.showMessageDialog(null,"Items no Facturado, genere la factura para poder exportarlo. Gracias");
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EditorDePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;
   
    
}

    @Override
    public ArrayList leerTango(int vendedor, String fecha,Connection bd) {
         try {
            ArrayList listadoP=new ArrayList();
            
            //Connection cc=(Connection)bd;
            String sql="select NRO_PEDIDO,FECHA_PEDI,RAZON_SOCI,TALON_PED,COD_VENDED from AR_PEDIDOS where COD_VENDED="+vendedor+" and FECHA_PEDI = '"+fecha+"' order by NRO_PEDIDO desc";
            
            Statement st=bd.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            Pedidos pedi;
            String pedidoViejo=null;
            String pedidoNuevo=null;
            while(rs.next()){
                pedidoViejo=rs.getString("NRO_PEDIDO");
                if(pedidoViejo.equals(pedidoNuevo)){
                    
                }else{
                    pedi=new Pedidos();
                    pedi.setNumeroPedidos(rs.getString("NRO_PEDIDO"));

                    pedi.setFechaDePedido(rs.getString("FECHA_PEDI"));
                    pedi.setRazonSocial(rs.getString("RAZON_SOCI"));
                    pedi.setEmpresa(rs.getString("TALON_PED"));
                    listadoP.add(pedi);
                    System.out.println(pedi.getRazonSocial());
                    pedidoNuevo=rs.getString("NRO_PEDIDO");
                }
            }
            
            rs.close();
            st.close();
            //Boolean cerrado=cnn.cerrarConeccion(cc);
            
            return listadoP;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public DefaultTableModel mostrarEnTabla(ArrayList listado) {
        DefaultTableModel modelo=new DefaultTableModel();
        Iterator it=listado.listIterator();
        Pedidos pedido;
        
        modelo.addColumn("FECHA PEDIDO");
        modelo.addColumn("NRO PEDIDO");
        modelo.addColumn("CLIENTE");
        Object[] fila=new Object[3];
        while(it.hasNext()){
            pedido=(Pedidos) it.next();
            fila[0]=pedido.getFechaDePedido();
            fila[1]=pedido.getNumeroPedidos();
            fila[2]=pedido.getRazonSocial();
            modelo.addRow(fila);
        }
        return modelo;
    }

    @Override
    public ArrayList leerDetalleTango(int vendedor, String fecha, Connection bd, String nroPedido) {
        try {
            ArrayList listadoP=new ArrayList();
            
            String sql="select * from ar_pedidos where NRO_PEDIDO like '"+nroPedido+"' order by ID_GVA03";
            Statement st=bd.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                PedidosParaReparto pedi=new PedidosParaReparto();
                pedi.setCodigoTangoDePedido(rs.getString("NRO_PEDIDO"));
                Double equivalencia=rs.getDouble("CAN_EQUI_V");
                Double pedidas=rs.getDouble("CANT_PEDID") / equivalencia;
                Double remitidas=rs.getDouble("cantidad_Descargada") / equivalencia;
                Double facturadas=rs.getDouble("cantidad_facturada") / equivalencia;
                pedi.setIdPedidosTango(rs.getInt("ID_GVA03"));
                Double cantidadFinal=0.00;
                if(remitidas==0){
                    cantidadFinal=0.00;
                }else{
                    if(pedidas==remitidas){
                        cantidadFinal=pedidas;
                    }else{
                    cantidadFinal=remitidas;
                    }
                }
                if(facturadas > 0){
                    if(pedi.getCantidadArticuloPendiente()> 0 ){
                    cantidadFinal=remitidas - facturadas;
                    }
                }
                pedi.setNumeroVendedor(rs.getInt("COD_VENDED"));
                pedi.setCantidadArticulo(pedidas);
                pedi.setCantidadArticuloPendiente(facturadas);
                pedi.setCantidadArticulosTotales(remitidas);
                pedi.setCodigoArticulo(rs.getString("COD_ARTICU"));
                pedi.setCodigoCliente(rs.getString("COD_CLIENT"));
                //pedi.setCodigoDeposito(rs.getInt(""));//FALTA EL CODIGO DE DEPOSITO
                pedi.setCondicionDeVenta(rs.getInt("COND_VTA"));
                pedi.setDescripcionArticulo(rs.getString("DESC_ARTIC")+rs.getString("DESC_ADIC"));
                pedi.setFechaPedidosTango(rs.getString("FECHA_PEDI"));
                String fechEnv=rs.getString("FECHA_PEDI");
                System.out.println("vamos a ver la fecha de envio "+fechEnv);
                //String trim = fechEnv.trim();
                if(fechEnv == null || fechEnv.equals("0")){
                    //pedi.setFechaEnvio("");
                    System.err.println("FUE FECHA NULA");
                            
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
                    fechEnv=dia+"/"+mes+"/"+ano;
                    pedi.setFechaEnvio(fechEnv);
                    System.err.println(" FECHA INICIALIZADA EN "+dia+"/"+mes+"/"+ano);
                }else{
                    pedi.setFechaEnvio(fechEnv);
                    System.out.println("fecha envio "+pedi.getFechaEnvio());
                }
                System.out.println("fecha envio "+pedi.getFechaEnvio());
                pedi.setNumeroComprobante("00");//FALTA NUMERO DE COMPROBANTE (N_REMITO)
                pedi.setObservaciones(rs.getString("LEYENDA_1"));
                pedi.setObservaciones1(rs.getString("LEYENDA_2"));
                pedi.setObservaciones2(rs.getString("LEYENDA_3"));
                pedi.setRazonSocial(rs.getString("RAZON_SOCI"));
                pedi.setiDPedido(rs.getInt("ID_GVA03"));
                String empresa;
                switch(rs.getInt("TALON_PED")){
                    case 6:
                        empresa="BU";
                        break;
                    case 7:
                        empresa="SD";
                        break;
                    case 10:
                        empresa="SRL";
                        break;
                    default:
                        empresa="SRL";
                        break;
                }
                pedi.setEmpresa(empresa);
                //pedi.setMarcadoParaProceso(rs.getInt("proceso"));
                //pedi.setMarcadoParaReparto(rs.getInt("reparto"));
                //pedi.setZonaAsignada(rs.getInt("zona"));
                //pedi.setAlertaAsignada(rs.getInt("alerta"));
                listadoP.add(pedi);
                System.out.println(pedi.getRazonSocial());
                
            }
            
            rs.close();
            st.close();
            //Boolean cerrado=cnn.cerrarConeccion(cc);
            
            return listadoP;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String validarEnviadoHdr(Object pedido) {
        try {
            //Boolean numerosRepetidos=true;
            ConeccionRemotaBdMysql mSql=new ConeccionRemotaBdMysql();
            Connection con=mSql.obtenerConeccion();
            Statement st=con.createStatement();
            ResultSet rs=null;
            String sql="";
            PedidosParaReparto ped=new PedidosParaReparto();
            
            String fechasPedidos=null;
            
            
                ped=(PedidosParaReparto)pedido;
                String descArtic="DUPLICACIÓN DE PEDIDOS \n";
                String numeroPed=ped.getCodigoTangoDePedido().substring(3);
                if(ped.getDescripcionArticulo().length()> 30){
                    descArtic=ped.getDescripcionArticulo().substring(0,30);
                }else{
                    descArtic=ped.getDescripcionArticulo();
                }
                sql="select pedidos_carga1.numero,pedidos_carga1.entrega from pedidos_carga1 where NRO_PEDIDO like '%"+numeroPed+"' and CANT_PEDID ="+ped.getCantidadArticulo()+" and TALON_PEDI ='"+ped.getEmpresa()+"' and ID_GVA03="+ped.getIdPedidosTango();
                
                st.execute(sql);
                rs=st.getResultSet();
                int h=0;
                while(rs.next()){
                    h++;
                    fechasPedidos+=" esta intentando duplicar el pedido del sr. "+ped.getRazonSocial()+" pasado ha sistema HDR para el día "+rs.getString("entrega")+"\n";
                }
                System.out.println(sql+" cant repetidos "+h);
                
                
           
                
                rs.close();
                st.close();
                mSql.cerrarConeccion(con);
                return fechasPedidos;
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGuardado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}
