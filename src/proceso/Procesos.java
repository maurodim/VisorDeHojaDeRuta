/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.text.ParseException;
import objetos.PedidosParaReparto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import objetos.Articulos;
import facturacion.clientes.ClientesTango;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.DetalleHdr;
import objetos.DetallePesosPedido;
import objetos.EncabezadoHdr;
import objetos.Fleteros;
import objetos.Listados;
import objetos.Vehiculos;
import visordehojaderuta.Coneccion;
import visordehojaderuta.ConeccionSqlTango;
//import siderconcapadatos.SiderconCapaatos;

/**
 *
 * @author USUARIO
 */
public class Procesos {
    static Connection cp=null;
    static ArrayList<PedidosParaReparto> listaPed=new ArrayList();
    static Coneccion cn;
    static int ultimoNumeroDeListado=0;
    static int ultimaRevisionDeListado=0;

    public Procesos() {
        cn=new Coneccion();
        cp=cn.getCn();
    }
    
    public Map cargarPesosDeArticulos() throws SQLException{
        Map<String,Double> articulos=new HashMap<String,Double>();
        
        //Integer pesoUnitario = 0;
        //Connection cp=cn.ObtenerConeccion();
        String sql="select PESOS.codigo,(select ArticulosDesc.Descripcion from ArticulosDesc where CodArticulo=PESOS.codigo),PESOS.peso,(select ArticulosDesc.Sinonimo from ArticulosDesc where CodArticulo=PESOS.codigo) from PESOS";
        Statement st=cp.createStatement();
        st.execute(sql);
        ResultSet rs=st.getResultSet();
        while(rs.next()){
            Articulos art=new Articulos();
            art.setCodigo(rs.getString(1));
            art.setDescripcionArticulo(rs.getString(2));
            art.setPesoUnitario(rs.getDouble(3));
            art.setSinonimoArticulo(rs.getString(4));
            //System.out.println(art.getCodigo());
            //pesoUnitario=String.valueOf(art.getPesoUnitario());
            String pesoUnitario=art.getCodigo();
            System.out.println(pesoUnitario.length()+" "+pesoUnitario+" peso "+art.getPesoUnitario()+" "+art.getDescripcionArticulo()+" sinonimo "+art.getSinonimoArticulo());
            //pesoUnitario++;
            articulos.put(pesoUnitario,art.getPesoUnitario());
        }
        rs.close();
        //cn.CerrarConneccion(cp);
        return articulos;
    }
    public Map DetallePedido(String numeroPedido) throws SQLException{
        Map<String,Double> detalle=new HashMap<String,Double>();
        //Connection cp=cn.ObtenerConeccion();
        String sql="select pedidos_carga1.COD_ARTIC,pedidos_carga1.NRO_PEDIDO,pedidos_carga1.CANT_PEDID from pedidos_carga1 where NRO_PEDIDO like '%"+numeroPedido+"%'";
        PreparedStatement st=cp.prepareStatement(sql);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            detalle.put(rs.getString("COD_ARTIC"),rs.getDouble("CANT_PEDID"));
        }
        rs.close();
        //cn.CerrarConneccion(cp);
        return detalle;
    
    }

    public ArrayList ListarPedidosPorFecha(String fechEnt) throws SQLException{
	/*
         * ACA SE GENERA UN ARRAY CON TODOS LOS OBJETOS PEDIDOS QUE CORRESPONDEN SER ENTREGADOS
         * EN LA FECHA PUNTUALIZADA. POR LO TANTO SE PUEDE VOLCAR A LA TABLA PARA SU SELECCION
         * 
         */
          //      Connection cp=cn.ObtenerConeccion();
		String sql="select *,(select TABLA1.actualizacion from TABLA1 where TABLA1.COD_CLI=pedidos_carga1.COD_CLIENT group by TABLA1.COD_CLI)as act,sum(pedidos_carga1.peso * pedidos_carga1.CANT_PEDID) as total from pedidos_carga1 where entrega like '"+fechEnt+"%'and reparto=1 group by NRO_PEDIDO order by RAZON_SOC";
		System.out.println(sql);
                
                PreparedStatement st=cp.prepareStatement(sql);
                //st.execute(sql);
		ResultSet rs=st.executeQuery();;
		//synchronized rs;
                while(rs.next()){
			PedidosParaReparto pedidos=new PedidosParaReparto();
			pedidos.setiDPedido(rs.getInt("numero"));
			pedidos.setRazonSocial(rs.getString("RAZON_SOC"));
			pedidos.setCodigoTangoDePedido(rs.getString("NRO_PEDIDO"));
			pedidos.setVehiculoAsignado(rs.getInt("vehiculo"));
			pedidos.setPesoTotal(rs.getDouble("total"));
                        pedidos.setCodigoArticulo(rs.getString("COD_ARTIC"));
                        pedidos.setDescripcionArticulo(rs.getString("DESC_ARTIC")+" "+rs.getString("DESC_ADIC"));
                        pedidos.setPesoItems(rs.getDouble("peso")* rs.getDouble("CANT_PEDID"));
                        pedidos.setCantidadArticulo(rs.getDouble("CANT_PEDID"));
			pedidos.setCodigoCliente(rs.getString("COD_CLIENT"));
                        pedidos.setFechaEnvio(rs.getString("entrega"));
                        pedidos.setFechaActualizacionSaldoCliente(rs.getDate("act"));
                        pedidos.setNumeroDeListadoDeMateriales(rs.getInt("listado"));
                        pedidos.setNumeroDeHojaDeRuta(rs.getInt("hdr1"));
                        pedidos.setNumeroDeProceso(rs.getInt("orden_num"));
                        pedidos.setNumeroDeFletero(rs.getInt("fletero"));
                        pedidos.setNumeroComprobante(rs.getString("N_REMITO"));
                        
                        String pendiente=String.valueOf(rs.getDouble("CANT_FACT"));
                        
                        Double articulosPendientes=0.00;
                        if(pendiente==null){
                            
                        }else{
                            articulosPendientes=Double.parseDouble(pendiente);
                        }
                        //pedidos.setCantidadArticulosEntregados(articulosPendientes);
                        //articulosPendientes=pedidos.getCantidadArticulo()- pedidos.getCantidadArticulosEntregados();
                        pedidos.setCantidadArticuloPendiente(articulosPendientes);
                        listaPed.add(pedidos);
		}
                rs.close();
                //ActualizarDatosPedidos act=new ActualizarDatosPedidos();
                //act.setPedidos(listaPed);
                //act.start();
            //    cn.CerrarConneccion(cp);
		return listaPed;
		
	}	
	public ArrayList ListarPedidosPorVehiculo(Integer vehiculo){
		ArrayList<PedidosParaReparto> listaUnidad=new ArrayList();
		//listaUnidad=pedido;
		Iterator iterador=listaPed.iterator();
		while(iterador.hasNext()){
			PedidosParaReparto ped=(PedidosParaReparto)iterador.next();
			if(vehiculo==ped.getVehiculoAsignado()){
                            System.out.println("NOMBRE PEDIDO LISTADO "+ped.getRazonSocial());
				listaUnidad.add(ped);
			}
		}
		return listaUnidad;
	}
	public ArrayList ListarVehiculos() throws SQLException{
		ArrayList<Vehiculos> listVehiculos=new ArrayList();
		//Statement st=cp.createStatement();
         //       Connection cp=cn.ObtenerConeccion();
                String sql="select * from unidades order by numero";
		PreparedStatement st=cp.prepareStatement(sql);
		 ResultSet rs=st.executeQuery();
		 while(rs.next()){
			 Vehiculos uni=new Vehiculos();
			 uni.setNumero(rs.getInt("numero"));
                         uni.setDescripcion(rs.getString("descripcion"));
                        uni.setPatente(rs.getString("patente"));
                        uni.setKilometrosActuales(rs.getInt("kilometrosActuales"));
                         uni.setCapacidadDeCarga(rs.getDouble("carga_max"));
			 listVehiculos.add(uni);
		 }
                 rs.close();
           //      cn.CerrarConneccion(cp);
		 return listVehiculos;
	}
        public ArrayList cargarHdrVehiculo(ArrayList veh,String fecha) throws SQLException{
            String sql=null;
            Vehiculos uni=new Vehiculos();
            Iterator iv=veh.listIterator();
            Statement st=null;
            while(iv.hasNext()){
                uni=(Vehiculos)iv.next();
                sql="select hdr.numero from hdr where numeroVehiculo="+uni.getNumero()+" and fechaEntrega like '"+fecha+"%'";
                st=cp.createStatement();
                st.execute(sql);
                ResultSet rs=st.getResultSet();
                while(rs.next()){
                    uni.setNumeroHdr(rs.getInt(1));
                }
                rs.close();
            }
            st.close();
            return veh;
        }
        public Map cargarSaldosDeClientes() throws SQLException{
            Map<String,Double> saldos=new HashMap<String,Double>();
            String cod;
            Double saldo=0.00;
           // Connection cp=cn.ObtenerConeccion();
            String sql="select TABLA1.COD_CLI,TABLA1.RAZON_SOC,SUM(TABLA1.IMPORTE),TABLA1.actualizacion from TABLA1 group by COD_CLI";
            PreparedStatement st=cp.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                ClientesTango cliente=new ClientesTango();
                cliente.setCodigoCliente(rs.getString("COD_CLI"));
                cliente.setRazonSocial(rs.getString("RAZON_SOC"));
                cliente.setSaldo(rs.getDouble("SUM(TABLA1.IMPORTE)"));
                cliente.setFechaActualizacion(rs.getDate("actualizacion"));
                cod=cliente.getCodigoCliente();
                saldo=cliente.getSaldo();
                System.out.println("cliente "+cliente.getRazonSocial()+" saldo :"+cliente.getSaldo());
                saldos.put(cod, saldo);
            }
            rs.close();
            //cn.CerrarConneccion(cp);
            return saldos;
        }
        public void guardarAsignacionDeVehiculos(ArrayList pedidosTrabajados) throws SQLException{
            PedidosParaReparto pedidos=new PedidosParaReparto();
           // Connection cp=cn.ObtenerConeccion();
            Iterator ii=pedidosTrabajados.listIterator();
            while(ii.hasNext()){
                pedidos=(PedidosParaReparto) ii.next();
                String sql="update pedidos_carga1 set vehiculo="+pedidos.getVehiculoAsignado()+" where NRO_PEDIDO like '%"+pedidos.getCodigoTangoDePedido()+"%' and entrega like '"+pedidos.getFechaEnvio()+"%'";
                Statement st=cp.createStatement();
                st.executeUpdate(sql);
                st.close();
                
            }
          //  cn.CerrarConneccion(cp);
        }
        public ArrayList detallePedidoParaCorreccion(String numeroPedido,String fecha) throws SQLException{
            //System.out.println(numeroPedido+" fecha "+fecha);
          //  Connection cp=cn.ObtenerConeccion();
            ArrayList detalles=new ArrayList();
            String sql="select * from pedidos_carga1 where NRO_PEDIDO like '%"+numeroPedido+"%' and entrega like '"+fecha+"%' and reparto=1";
            PreparedStatement st=cp.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                PedidosParaReparto pedido=new PedidosParaReparto();
                pedido.setCodigoTangoDePedido(numeroPedido);
                pedido.setCodigoArticulo(rs.getString("COD_ARTIC"));
                pedido.setDescripcionArticulo(rs.getString("DESC_ARTIC")+rs.getString("DESC_ADIC"));
                pedido.setCantidadArticulo(rs.getDouble("CANT_PEDID"));
                pedido.setCantidadArticuloPendiente(rs.getDouble("CANT_FACT"));
                if(pedido.getCantidadArticuloPendiente()==null){
                    pedido.setCantidadArticuloPendiente(0.00);
                }
                pedido.setFechaEnvio(rs.getString("entrega"));
                pedido.setiDPedido(rs.getInt("numero"));
                pedido.setIdPedidosTango(rs.getInt("ID_GVA03"));
                pedido.setEmpresa(rs.getString("TALON_PEDI"));
                pedido.setCantidadOriginal(rs.getDouble("cantOriginal"));
                System.out.println("pedido :"+numeroPedido+" fecha"+fecha+" articulo: "+pedido.getDescripcionArticulo()+" emprsa "+pedido.getEmpresa());
                detalles.add(pedido);
            }
            rs.close();
          //  cn.CerrarConneccion(cp);
            return detalles;
        }
        public void guardarDetallePedido(ArrayList detalles) throws SQLException{
          //  Connection cp=cn.ObtenerConeccion();
            PedidosParaReparto pedidos=new PedidosParaReparto();
            Iterator ui=detalles.listIterator();
            while(ui.hasNext()){
                pedidos=(PedidosParaReparto) ui.next();
                String sql="update pedidos_carga1 set CANT_PEDID="+pedidos.getCantidadArticulo()+",CANT_FACT="+pedidos.getCantidadArticuloPendiente()+",entrega ='"+pedidos.getFechaEnvio()+"',CANT_DESC="+pedidos.getCantidadArticuloPendiente()+",listado="+pedidos.getNumeroDeListadoDeMateriales()+",vehiculo="+pedidos.getVehiculoAsignado()+" where numero="+pedidos.getiDPedido();
                System.out.println(sql);
                Statement st=cp.createStatement();
                st.executeUpdate(sql);
                st.close();
                
            }
         //   cn.CerrarConneccion(cp);
}
	public ArrayList listadoDetalladoPorVehiculo(int numeroVehiculo,String fecha) throws SQLException{
                ArrayList listadoDetallado=new ArrayList();
         //      Connection cp=cn.ObtenerConeccion();
                String sql="select * from pedidos_carga1 where vehiculo="+numeroVehiculo+" and entrega like '"+fecha+"%'";
                PreparedStatement st=cp.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
              // PedidosParaReparto ped1 = null;
 			PedidosParaReparto pedidos=null;
			pedidos.setiDPedido(rs.getInt("numero"));
			pedidos.setRazonSocial(rs.getString("RAZON_SOC"));
			pedidos.setCodigoTangoDePedido(rs.getString("NRO_PEDIDO"));
			pedidos.setVehiculoAsignado(rs.getInt("vehiculo"));
			pedidos.setPesoTotal(rs.getDouble("peso"));
                        pedidos.setCodigoArticulo(rs.getString("COD_ARTIC"));
                        pedidos.setDescripcionArticulo(rs.getString("DESC_ARTIC")+" "+rs.getString("DESC_ADIC"));
                        pedidos.setPesoItems(rs.getDouble("peso")* rs.getDouble("CANT_PEDID"));
                        pedidos.setCantidadArticulo(rs.getDouble("CANT_PEDID"));
			pedidos.setCodigoCliente(rs.getString("COD_CLIENT"));
                        pedidos.setFechaEnvio(rs.getString("entrega"));
                        String pendiente=String.valueOf(rs.getDouble("CANT_FACT"));
                        Double articulosPendientes=0.00;
                        if(pendiente==null){
                            articulosPendientes=0.00;
                        }else{
                            articulosPendientes=Double.parseDouble(pendiente);
                        }
                        //pedidos.setCantidadArticuloPendiente(articulosPendientes);
                        //articulosPendientes=pedidos.getCantidadArticulo()- pedidos.getCantidadArticulosEntregados();
                        pedidos.setCantidadArticuloPendiente(articulosPendientes);
	      		listadoDetallado.add(pedidos); 
            }
         //   cn.CerrarConneccion(cp);
	    return listadoDetallado;
            
        }
        public ArrayList ListadoDeArticulos() throws SQLException{
        //    Connection cp=cn.ObtenerConeccion();
            ArrayList articulos = new ArrayList();
            String sql="select PESOS.codigo,(select ArticulosDesc.Descripcion from ArticulosDesc where CodArticulo=PESOS.codigo),PESOS.peso,(select ArticulosDesc.Sinonimo from ArticulosDesc where CodArticulo=PESOS.codigo) from PESOS order by codigo";
            int cantidad=0;
        Statement st=cp.createStatement();
        st.execute(sql);
        ResultSet rs=st.getResultSet();
        while(rs.next()){
            Articulos art = new Articulos();
            art.setCodigo(rs.getString(1));
            art.setDescripcionArticulo(rs.getString(2));
            art.setPesoUnitario(rs.getDouble(3));
            art.setSinonimoArticulo(rs.getString(4));
            //System.out.println(art.getCodigo());
            //pesoUnitario=String.valueOf(art.getPesoUnitario());
            String pesoUnitario=art.getCodigo();
            cantidad++;
            System.out.println(cantidad+" "+pesoUnitario+" peso "+art.getPesoUnitario());
            //pesoUnitario++;
            articulos.add(art);
        }
        rs.close();
       // cn.CerrarConneccion(cp);
        return articulos;
        }
        public ArrayList ListarFleteros() throws SQLException{
            //Fleteros conductor=new Fleteros();
       //     Connection cp=cn.ObtenerConeccion();
            ArrayList lista=new ArrayList();
            String sql="select * from fleteros order by numero";
            Statement st=cp.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                Fleteros conductor=new Fleteros();
                conductor.setNumeroFletero(rs.getInt("numero"));
                conductor.setNombreFletero(rs.getString("nombre"));
                conductor.setCelularFletero(rs.getString("celular"));
                lista.add(conductor);
            }
            rs.close();
       //     cn.CerrarConneccion(cp);
            return lista;
        }
        public void GuardarModificacionesVehiculos(ArrayList unidades) throws SQLException{
            Vehiculos uni=new Vehiculos();
            String sql=null;
       //     Connection cp=cn.ObtenerConeccion();
            //System.out.println(sql);
            //Statement st=cp.createStatement();
            Iterator iu=unidades.listIterator();
            while(iu.hasNext()){
                uni=(Vehiculos)iu.next();
                String patente=uni.getPatente();
                Double cargar=uni.getCapacidadDeCarga();
                Integer kilometros=uni.getKilometrosActuales();
                if((patente==null) ||(patente==""))patente="N/D";
                System.out.println("patente "+patente);
                if(cargar!=null)cargar=0.00;
                if(kilometros==null)kilometros=0;
                System.out.println("kilometros "+kilometros);
                sql="update unidades set patente ='"+patente+"', kilometrosActuales="+kilometros+" where numero="+uni.getNumero();
                System.out.println(sql);
                Statement st=cp.createStatement();
                st.executeUpdate(sql);
                System.out.println(sql);
                st.close();
            }
       //     cn.CerrarConneccion(cp);
            
        }
        public void GuardarDatosFleteros(ArrayList listFleteros) throws SQLException{
                Fleteros chofer=new Fleteros();
        //        Connection cp=cn.ObtenerConeccion();
                Iterator ic=listFleteros.listIterator();
                while(ic.hasNext()){
                    chofer=(Fleteros)ic.next();
                    Integer numero=chofer.getNumeroFletero();
                    String nombre=chofer.getNombreFletero();
                    String celular=chofer.getCelularFletero();
                    Integer condicion=chofer.getCondicion();
                    if(condicion==null)condicion=0;
                    System.out.println(condicion);
                    String sql=null;
                    switch(condicion){
                        case 2:
                             sql="delete from fleteros where numero="+numero;   
                            break;
                        case 3:
                            sql="insert into fleteros (nombre,celular) values ('"+nombre+"','"+celular+"')";
                            break;
                        default:
                            sql="update fleteros set nombre ='"+nombre+"',celular='"+celular+"' where numero="+numero;
                            break;
                    }
                    Statement st=cp.createStatement();
                    st.executeUpdate(sql);
                    st.close();
          //          cn.CerrarConneccion(cp);
                    System.out.println(sql);
                }
        }
        public void NuevoDatoVehiculos(String descripcion,String patente,Double capacidad,Integer kilometros) throws SQLException{
          //  Connection cp=cn.ObtenerConeccion();
            String sql="insert into unidades (descripcion,patente,carga_max,kilometrosActuales) values('"+descripcion+"','"+patente+"','"+capacidad+"','"+kilometros+"')";
            Statement st=cp.createStatement();
            st.executeUpdate(sql);
            st.close();
          //  cn.CerrarConneccion(cp);
        }
        public void EliminarVehiculo(int numero) throws SQLException{
          //  Connection cp=cn.ObtenerConeccion();
            String sql="delete from unidades where numero="+numero;
            Statement st=cp.createStatement();
            st.executeUpdate(sql);
            st.close();
         //   cn.CerrarConneccion(cp);
        }
        public void ModificacionDeArticulos(ArrayList artic) throws SQLException{
            Iterator ita=artic.listIterator();
        //    Connection cp=cn.ObtenerConeccion();
            String sql=null;
            Articulos ar=new Articulos();
            while(ita.hasNext()){
                ar=(Articulos)ita.next();
                switch(ar.getEstado()){
                    case 2:
                        sql="delete from PESOS where codigo='"+ar.getCodigo()+"'";
                        Statement st=cp.createStatement();
                        st.executeUpdate(sql);
                        //st.close();
                        sql="delete from ArticulosDesc where CodArticulo='"+ar.getCodigo()+"'";
                        //Statement st=cp.createStatement();
                        st.executeUpdate(sql);
                        st.close();
                        break;
                    case 3:
                        sql="insert into PESOS (codigo,peso) values('"+ar.getCodigo()+"',"+ar.getPesoUnitario()+")";
                        Statement sh=cp.createStatement();
                        sh.executeUpdate(sql);
                        sql="insert into ArticulosDesc (CodArticulo,Descripcion,Sinonimo) values ('"+ar.getCodigo()+"','"+ar.getDescripcionArticulo()+"','"+ar.getSinonimoArticulo()+"')";
                        sh.executeUpdate(sql);
                        sh.close();
                        break;
                    default:
                        sql="update PESOS set peso="+ar.getPesoUnitario()+" where PESOS.codigo='"+ar.getCodigo()+"'";
                        Statement sp=cp.createStatement();
                        sp.executeUpdate(sql);
                        //st.close();
                        sql="update ArticulosDesc set Descripcion='"+ar.getDescripcionArticulo()+"',Sinonimo='"+ar.getSinonimoArticulo()+"' where CodArticulo='"+ar.getCodigo()+"'";
                        //Statement st=cp.createStatement();
                        sp.executeUpdate(sql);
                        sp.close();
                        break;
                }
            }
         //   cn.CerrarConneccion(cp);
        }
        public void GuardarNuevoArticulo(Articulos ar) throws SQLException{
         //   Connection cp=cn.ObtenerConeccion();    
            String sql="insert into PESOS (codigo,peso) values("+ar.getCodigo()+","+ar.getPesoUnitario()+")";
                System.out.println(sql);        
                Statement sh=cp.createStatement();
                sh.executeUpdate(sql);
                sql="insert into ArticulosDesc (CodArticulo,Descripcion,Sinonimo) values ("+ar.getCodigo()+",'"+ar.getDescripcionArticulo()+"','"+ar.getSinonimoArticulo()+"')";
                System.out.println(sql);
                sh.executeUpdate(sql);
                sh.close();
         //       cn.CerrarConneccion(cp);
        }
        public Double pesoTotalPedido(String numeroPedido,String fech) throws SQLException{
        //    Connection cp=cn.ObtenerConeccion();
            DetallePesosPedido detalle=new DetallePesosPedido();
            Double pesoTotal=0.00;
            Double pesoUnitario=0.00;
            Double cantidad=0.00;
            String sql="select pedidos_carga1.COD_ARTIC,pedidos_carga1.CANT_PEDID,(select PESOS.peso from PESOS where codigo = pedidos_carga1.COD_ARTIC),pedidos_carga1.entrega,pedidos_carga1.NRO_PEDIDO from pedidos_carga1 where NRO_PEDIDO ='"+numeroPedido+"' and entrega like '"+fech+"%'";
            Statement st=cp.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                cantidad=rs.getDouble(2);
                pesoUnitario=rs.getDouble(3);
                pesoTotal+=pesoUnitario * cantidad;
            }
            rs.close();
            st.close();
      //      cn.CerrarConneccion(cp);
            return pesoTotal;
            
        }
        public ArrayList listadoDeMaterialesPendientes() throws SQLException{
        //    Connection cp=cn.ObtenerConeccion();
            ArrayList listado=new ArrayList();
            String sql="select pedidos_carga1.NRO_PEDIDO,pedidos_carga1.RAZON_SOC,pedidos_carga1.COD_CLIENT,pedidos_carga1.COD_ARTIC,pedidos_carga1.DESC_ARTIC,sum(pedidos_carga1.CANT_FACT),pedidos_carga1.CANT_DESC,pedidos_carga1.CANT_PEDID,pedidos_carga1.numero from pedidos_carga1 where CANT_FACT > 0 group by COD_ARTIC,RAZON_SOC order by RAZON_SOC";
            Statement st=cp.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                PedidosParaReparto ped=new PedidosParaReparto();
                ped.setCodigoTangoDePedido(rs.getString(1));
                ped.setRazonSocial(rs.getString(2));
                ped.setCodigoCliente(rs.getString(3));
                ped.setCodigoArticulo(rs.getString(4));
                ped.setDescripcionArticulo(rs.getString(5));
                Double pendiente=rs.getDouble(6);
                Double totales=rs.getDouble(7);
                Double art=rs.getDouble(8);
                art=totales - pendiente;
                ped.setCantidadArticuloPendiente(pendiente);
                ped.setCantidadArticulosTotales(totales);
                ped.setCantidadArticulo(art);
                ped.setiDPedido(rs.getInt(9));
                listado.add(ped);
                System.out.println("pendientes "+pendiente+"/"+totales+"/"+art);
                
            }
            rs.close();
            st.close();
       //     cn.CerrarConneccion(cp);
            return listado;
        }
        public void GuardarModificacionesPendientes(ArrayList pendientes) throws SQLException{
         //   Connection cp=cn.ObtenerConeccion();
            PedidosParaReparto pedidos=new PedidosParaReparto();
            Iterator ipp=pendientes.listIterator();
            String sql=null;
            Statement st=cp.createStatement();
            System.err.println(pendientes.size());
            while(ipp.hasNext()){
                pedidos=(PedidosParaReparto)ipp.next();
                
                switch(pedidos.getEstado()){
                    case 1:
                        //Statement st=cp.createStatement();
                        sql="insert into pedidos_carga1 (NRO_PEDIDO,CANT_PEDID,entrega,CANT_DESC,COD_CLIENT,RAZON_SOC,COD_ARTIC,DESC_ARTIC,vehiculo,peso,reparto,numeroOriginal) values ('"+pedidos.getCodigoTangoDePedido()+"',"+pedidos.getCantidadArticulo()+",'"+pedidos.getFechaEnvio()+"',"+pedidos.getCantidadArticulosTotales()+",'"+pedidos.getCodigoCliente()+"','"+pedidos.getRazonSocial().trim()+"','"+pedidos.getCodigoArticulo()+"','"+pedidos.getDescripcionArticulo()+"',0,0.00,1,"+pedidos.getiDPedido()+")";
                        //sql="update pedidos_carga1 set NRO_PEDIDO='"+pedidos.getCodigoTangoDePedido()+"',CANT_PEDID="+pedidos.getCantidadArticulo()+",CANT_FACT="+pedidos.getCantidadArticuloPendiente()+",entrega='"+pedidos.getFechaEnvio()+"' where numero="+pedidos.getiDPedido();
                        System.out.println(sql);
                        
                        st.executeUpdate(sql);
                       
                         sql="update pedidos_carga1 set CANT_FACT="+pedidos.getCantidadArticuloPendiente()+" where numero="+pedidos.getiDPedido();
                        System.err.println("esta haciendo update "+sql);
                        
                        st.executeUpdate(sql);
                        st.close();

                        break;
                    case 2:
                        
                        sql="insert into pedidos_carga1 (NRO_PEDIDO,CANT_PEDID,entrega,CANT_DESC,COD_CLIENT,RAZON_SOC,COD_ARTIC,DESC_ARTIC,vehiculo,peso,reparto,motivo_anulacion) values ('"+pedidos.getCodigoTangoDePedido()+"',0,'00/00/0000',"+pedidos.getCantidadArticulosTotales()+",'"+pedidos.getCodigoCliente()+"','"+pedidos.getRazonSocial().trim()+"','"+pedidos.getCodigoArticulo()+"','"+pedidos.getDescripcionArticulo()+"',0,0.00,1,'eliminado por sistema hdr')";
                        //sql="update pedidos_carga1 set NRO_PEDIDO='"+pedidos.getCodigoTangoDePedido()+"',CANT_PEDID="+pedidos.getCantidadArticulo()+",CANT_FACT="+pedidos.getCantidadArticuloPendiente()+",entrega='"+pedidos.getFechaEnvio()+"' where numero="+pedidos.getiDPedido();
                        System.out.println(sql);
                        
                        st.executeUpdate(sql);
                         sql="update pedidos_carga1 set CANT_FACT=0 where numero="+pedidos.getiDPedido();
                         st.executeUpdate(sql);
                        st.close();
                        break;
                    default:
                        break;
                }
              }
         //   cn.CerrarConneccion(cp);
        }
        public ArrayList DetalleDeEntregasMaterialesPendientes(String pedido) throws SQLException{
            ArrayList detalle=new ArrayList();
        //    Connection cp=cn.ObtenerConeccion();
            String sql="select * from pedidos_carga1 where NRO_PEDIDO like '%"+pedido+"' and CANT_DESC > 0 order by RAZON_SOC";
            Statement st=cp.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                PedidosParaReparto pedi=new PedidosParaReparto();
                pedi.setRazonSocial(rs.getString("RAZON_SOC"));
                pedi.setCantidadArticulo(rs.getDouble("CANT_PEDID"));
                pedi.setFechaEnvio(rs.getString("entrega"));
                pedi.setCodigoArticulo(rs.getString("COD_ARTIC"));
                pedi.setDescripcionArticulo(rs.getString("DESC_ARTIC"));
                pedi.setVehiculoAsignado(rs.getInt("vehiculo"));
                detalle.add(pedi);
            }
            rs.close();
            st.close();
            
            return detalle;
        }
        public Listados GenerarNuevoListado(int vehiculo,String fecha) throws SQLException{
            System.out.println(vehiculo+" "+fecha);
            Listados list=new Listados();
            Boolean chq=ChequearListado(vehiculo,fecha);
            System.out.println(vehiculo+" "+fecha+" "+chq);
            if(chq){
                //ultimaRevisionDeListado;
                list.setNumeroListado(ultimoNumeroDeListado);
                list.setNumeroRevision(ultimaRevisionDeListado);
                return list;
            }else{
            String sql="insert into listadosDeMateriales (fechaEntrega,vehiculo) values ('"+fecha+"',"+vehiculo+")";
            Statement st=cp.createStatement();
            st.executeUpdate(sql);
            //st.close();
            sql="select listadosDeMateriales.numero from listadosDeMateriales order by numero";
            //Statement ss=cp.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                ultimoNumeroDeListado=rs.getInt(1);
            }
            list.setNumeroListado(ultimoNumeroDeListado);
            list.setNumeroRevision(0);
            rs.close();
            st.close();
            System.err.println(sql+" "+chq);
            return list;
            }
        }
        private Boolean ChequearListado(int vehiculo,String fecha) throws SQLException{
            String sql="select * from listadosDeMateriales where fechaEntrega like '"+fecha+"%' and vehiculo="+vehiculo;
            Boolean ccch = false;
            try{
            Statement st=cp.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
 
             //ccch= false;   

            while(rs.next()){
                ultimoNumeroDeListado=rs.getInt(1);
                ultimaRevisionDeListado=rs.getInt(4);
                ccch=true;
            
            }
            rs.close();
            st.close();
            return ccch;
            }catch(Exception ex){
                System.err.println("ERROR DE CONEXION PARA SACAR NUMERO DE LISTADO");
                return ccch;
            }
        }
        public void GuardarNumeroListadoEnPedido(ArrayList pedidos) throws SQLException{
            Iterator iip=pedidos.listIterator();
            String sql=null;
            PedidosParaReparto pe=new PedidosParaReparto();
            
            while(iip.hasNext()){
                pe=(PedidosParaReparto)iip.next();
                sql="update pedidos_carga1 set listado="+pe.getNumeroDeListadoDeMateriales()+" where NRO_PEDIDO ='"+pe.getCodigoTangoDePedido()+"'";
                Statement st=cp.createStatement();
                st.executeUpdate(sql);
                st.close();
                System.out.println(sql);
            }
            
        }
        public ArrayList UltimaHdr() throws SQLException{
            ArrayList listadoEnc=new ArrayList();
            EncabezadoHdr enc=new EncabezadoHdr();
            String sql="select hdr.numero,hdr.kmInicio,hdr.kmFinal,hdr.numeroFletero,hdr.numeroVehiculo,hdr.pesoCarga,hdr.listadoNumero,hdr.fechaEntrega,hdr.horaInicio,hdr.horaFinal,(select encabezado_otpc.peso_total from encabezado_otpc where encabezado_otpc.numero=hdr.listadoNumero),(select unidades.descripcion from unidades where unidades.numero=hdr.numeroVehiculo),(select fleteros.nombre from fleteros where fleteros.numero=hdr.numeroFletero),hdr.fechaImpresion,hdr.estado,hdr.motivoAnulacion from hdr order by numero";
            Statement st=cp.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                enc=new EncabezadoHdr();
                enc.setNumero(rs.getInt(1));
                enc.setKmIniciales(rs.getInt(2));
                enc.setKmFinales(rs.getInt(3));
                enc.setNumeroOperador(rs.getInt(4));
                enc.setNumeroVehiculo(rs.getInt(5));
                enc.setPesoEntregado(rs.getDouble(6));
                enc.setNumeroListado(rs.getInt(7));
                enc.setFechaReparto(rs.getString(8));
                String horaS=rs.getString(9);
                String horaF=rs.getString(10);
                enc.setPesoListado(rs.getDouble(11));
                enc.setDescripcionVehiculo(rs.getString(12));
                enc.setNombreOperador(rs.getString(13));
                enc.setFechaImpresion(rs.getString(14));
                enc.setEstadoHdr(rs.getInt(15));
                enc.setMotivoAnulacion(rs.getString(16));
                System.err.println(" horas "+horaS+" "+horaF);
                Integer hrS=Integer.parseInt(horaS.substring(0,2));
                Integer minS=Integer.parseInt(horaS.substring(3,4));
                Integer hrF=Integer.parseInt(horaF.substring(0, 2));
                Integer minF=Integer.parseInt(horaF.substring(3,4));
                enc.setHoraSalida(hrS);
                enc.setMinutosSalida(minS);
                enc.setHoraLlegada(hrF);
                enc.setMinutosLlegada(minF);
                listadoEnc.add(enc);
            }
            rs.close();
            st.close();
            return listadoEnc;
        }
        public ArrayList cargarDetalleHdr(Integer numeroHdr) throws SQLException{
            ArrayList detalle=new ArrayList();
            String sql="select detalle_hdr.cliente,detalle_hdr.numero_cli,detalle_hdr.comprobante,detalle_hdr.importe,detalle_hdr.entregado,detalle_hdr.motivoFallido,detalle_hdr.numero,detalle_hdr.reenviar,(select pedidos_carga1.NRO_PEDIDO from pedidos_carga1 where pedidos_carga1.hdr1=detalle_hdr.hdr and pedidos_carga1.COD_CLIENT=detalle_hdr.numero_cli group by pedidos_carga1.NRO_PEDIDO limit 0,1) from detalle_hdr where hdr="+numeroHdr;
            System.out.println(sql);
            Boolean entreg=true;
            Statement st=cp.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                DetalleHdr det=new DetalleHdr();
                det.setRazonSocial(rs.getString(1));
                det.setCodigoCliente(rs.getString(2));
                det.setNumeroDeComprobante(rs.getString(3));
                det.setSaldo(rs.getString(4));
                if(rs.getInt(5)==1){
                    entreg=false;
                }else{
                    entreg=true;
                }
                det.setEntregaCompletada(entreg);
                det.setMotivoFaltaDeEntrega(rs.getString(6));
                det.setNumero(rs.getInt(7));
                det.setReenviarPedido(rs.getInt(8));
                det.setNumeroPedidoTango(rs.getString(9));
                System.out.println("numero de pedido"+det.getNumeroPedidoTango());
                detalle.add(det);
            }
            rs.close();
            st.close();
            return detalle;
        }
        public ArrayList actualizarComprobantesPedidos(ArrayList cargados) throws SQLException, ParseException{
            PedidosParaReparto ped=new PedidosParaReparto();
            ArrayList resultado=new ArrayList();
            Iterator ic=cargados.listIterator();
            String sql=null;
            String fechaActual=null;
            SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd");
            DecimalFormat fr=new DecimalFormat("00");
            DecimalFormat cf=new DecimalFormat("####.##");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        //da=da - 1;
        dia=fr.format(da);
        mes=fr.format(me);
        fechaActual=ano+"-"+mes+"-"+dia;
            Statement st=cp.createStatement();
            while(ic.hasNext()){
                ped=(PedidosParaReparto)ic.next();
                String comprobante=" ";
                System.out.println("ACTUALIZANDO PEDIDOS "+ped.getiDPedido());
                //sql="truncate TABLA2";
                //st.executeUpdate(sql);
                sql="select TABLA2.N_COMP_REM from TABLA2 where COD_CLI like '%"+ped.getCodigoCliente()+"' and FECHA_REM like '%"+fechaActual+"%' group by N_COMP_REM";
                st.execute(sql);
                System.out.println("COMPROBANTES QUE LEE EL SISTEMA "+sql);
                ResultSet rs=st.getResultSet();
                String momentaneo=" ";
                while(rs.next()){
                        momentaneo=rs.getString(1);
       
                            comprobante+=momentaneo; 
                            System.out.println("comprobante encontrado "+comprobante);
                
                       
                }
                //comprobante=" ";
                rs.close();
                Integer listado=0;
                String cli=null;
                Double saldo=0.00;
                sql="select pedidos_carga1.listado,pedidos_carga1.TALON_PEDI,pedidos_carga1.orden_num,pedidos_carga1.COND_VENTA from pedidos_carga1 where numero="+ped.getiDPedido();
                st.execute(sql);
                ResultSet rr=st.getResultSet();
                while(rr.next()){
                listado=rr.getInt(1);
                ped.setEmpresa(rr.getString(2));
                ped.setNumeroDeProceso(rr.getInt(3));
                ped.setCondicionDeVenta(rr.getInt(4));
                cli=ped.getCodigoCliente();
                cli.trim();
                ClientesTango cliente=new ClientesTango();
                //saldo=(Double) VisorDeHojaDeRuta.saldoCliente.get(cli);
                //Double sal=Math.ceil(saldo);
                //saldo=sal;
                ped.setSaldoCliente(saldo);
                System.err.println("SALDO DEL CLIENTE "+saldo);
                }
                rr.close();
                ped.setNumeroComprobante(comprobante);
                ped.setNumeroDeListadoDeMateriales(listado);
                resultado.add(ped);
            }
            st.close();
            return resultado;
        } 
        public ArrayList leerMotivosFallidos() throws SQLException{
            ArrayList listado=new ArrayList();
            String sql="select * from motivosentregasfallidas order by numero";
            Coneccion con=new Coneccion();
            Connection cnn=con.ObtenerConeccion();
            Statement st=cnn.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                listado.add(rs.getString("descripcion"));
            }
            rs.close();
            st.close();
            con.CerrarConneccion(cnn);
            return listado;
        }
        public int leerNumeroMotivoElegido(String seleccion) throws SQLException{
                      int elegido=0;
            String sql="select * from motivosentregasfallidas where descripcion like '"+seleccion+"%'";
            Coneccion con=new Coneccion();
            Connection cnn=con.ObtenerConeccion();
            Statement st=cnn.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                elegido=rs.getInt("numero");
            }
            rs.close();
            st.close();
            con.CerrarConneccion(cnn);
            return elegido;
  
        }
        public ArrayList listadoDeDatos(Object ob){
            ClientesTango cli=new ClientesTango();
            ArrayList lst=new ArrayList();
            Coneccion con=new Coneccion();
            Connection cnn=con.ObtenerConeccion();
            
            
            return lst;
        }
        public PedidosParaReparto regenerarCantidadesTango(PedidosParaReparto ped){
        try {            
            Connection conT=ConeccionSqlTango.ObtenerConeccion(ped.getEmpresa());
            
            Statement st=null; 
            try {
                    st=conT.createStatement();
                    String codigoDeArticulo=ped.getCodigoArticulo().trim();
                    String numPed=ped.getCodigoTangoDePedido().substring(3);
                    String sql="select GVA03.CAN_EQUI_V,GVA03.CANT_A_DES from GVA03 where COD_ARTICU = '"+codigoDeArticulo+"' and NRO_PEDIDO like '%"+numPed+"'";
                    System.out.println("SQLLLLLL "+sql);
                    st.execute(sql);
                    ResultSet rs=st.getResultSet();
                    Double cantTango=0.00;
                    Double cantEqui=0.00;
                    Double cantTotal=0.00;
                    while(rs.next()){
                        cantTango=rs.getDouble("CANT_A_DES");
                        cantEqui=rs.getDouble("CAN_EQUI_V");
                        cantTotal=cantTango / cantEqui;
                    }
                    ped.setCantidadArticuloPendiente(cantTotal);
                    System.err.println("cantidades "+cantTotal);
                    ped.setFechaEnvio("00/00/0000");
                    ped.setNumeroDeListadoDeMateriales(0);
                    ped.setVehiculoAsignado(0);
                    rs.close();
                    st.close();
            } catch (SQLException ex) {
                    Logger.getLogger(Procesos.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Procesos.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ped;
        }
        public ArrayList DetallePedidoCompleto(String numeroPedido) throws SQLException{
                        //System.out.println(numeroPedido+" fecha "+fecha);
          //  Connection cp=cn.ObtenerConeccion();
            ArrayList detalles=new ArrayList();
            String sql="select * from pedidos_carga1 where NRO_PEDIDO like '%"+numeroPedido+"%' and reparto=1";
            PreparedStatement st=cp.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                PedidosParaReparto pedido=new PedidosParaReparto();
                pedido.setCodigoTangoDePedido(numeroPedido);
                pedido.setCodigoArticulo(rs.getString("COD_ARTIC"));
                pedido.setDescripcionArticulo(rs.getString("DESC_ARTIC")+rs.getString("DESC_ADIC"));
                pedido.setCantidadArticulo(rs.getDouble("CANT_PEDID"));
                pedido.setCantidadArticuloPendiente(rs.getDouble("CANT_FACT"));
                if(pedido.getCantidadArticuloPendiente()==null){
                    pedido.setCantidadArticuloPendiente(0.00);
                }
                pedido.setFechaEnvio(rs.getString("entrega"));
                pedido.setiDPedido(rs.getInt("numero"));
                pedido.setIdPedidosTango(rs.getInt("ID_GVA03"));
                System.out.println("pedido :"+numeroPedido+" articulo: "+pedido.getDescripcionArticulo());
                detalles.add(pedido);
            }
            rs.close();
          //  cn.CerrarConneccion(cp);
            return detalles;

        }
}
