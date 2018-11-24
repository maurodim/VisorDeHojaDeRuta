/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfacesPrograma.Busquedas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import visordehojaderuta.Coneccion;
//import proceso.Coneccion;

/**
 *
 * @author USUARIO
 */
public class PedidosParaReparto implements Busquedas{

    public PedidosParaReparto() {
        this.descripcionArticulo="";
        this.cantidadArticulo=0.00;
        this.cantidadArticuloPendiente=0.00;
        //this.fechaEnvio="00/00/0000";
        this.marcadoParaProceso=0;
        this.marcadoParaReparto=0;
        this.zonaAsignada=0;
        this.alertaAsignada=0;
        this.motivoFallido="";
        
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
        this.fechaEnvio=dia+"/"+mes+"/"+ano;
        System.err.println(" FECHA INICIALIZADA EN "+dia+"/"+mes+"/"+ano);

    }
	/*
	 * ACA SOLAMENTE GENERO EL OBJETO PEDIDO CON LOS DATOS ESENCIALES PARA TRABAJAR LAS 
	 * PRIMERAS PANTALLAS SIN CARGA DE CALCULOS
	 * EL IDPEDIDO DEBE SER UNICO PARA CADA PEDIDO ENVIADO, EL CALCULO DEL PESO Y EL DESDOBLAMIENTO 
	 * DE LA INFORMACION EN LAS DISTINTAS BASES QUE LO HAGA EL SISTEMA DE EXPORTACION DE PEDIDOS
	 * 
	 * TENER EN CUENTA QUE LA GRILLA INICIAL VA A LISTAR ESTOS POR FECHA Y LOS VA A TRABAJAR MODIFICANDO SU CARGA
	 * LA VARIABLE ENTREGACOMPELTADA ES PARA DETERMINAR SI TIENE MATERIALES PENDIENTES
	 * ESE PEDIDO ( PARA REENVIARLO SE GENERA UN NUEVO IDPEDIDO PERO SE 
	 * MANTIENE EL NUMERO DE PEDIDO DE TANGO
     * PARA GENERAR LA INDEPENDENCIA DE ESTE SISTEMA A TANGO Y MANEJARLO  EN FORMA DISTINTA
	 */
	private Integer iDPedido;
	private String codigoTangoDePedido;
	private String razonSocial;
        private String codigoCliente;
	private Double pesoTotal;
	private String fechaEnvio;
	private Integer vehiculoAsignado;
	private Boolean entregaCompletada;
	private Connection coneccionPedidos=Coneccion.ObtenerConeccion();
	private Integer numeroDeProceso;
        private Boolean confirmacionPorceso;
        private Integer codigoDeposito;
        private String codigoArticulo;
        private String descripcionArticulo;
        private Double cantidadArticulo;
        private Double cantidadArticuloPendiente;
        private Double cantidadArticulosTotales;
        private Double pesoItems;
        private Double saldoCliente;
        private Integer condicionDeVenta;
        private Integer condicionEstadoDelPedido;
        private int estado;
        private Integer numeroDeListadoDeMateriales;
        private Integer numeroDeHojaDeRuta;
        private String fechaPedidosTango;
        private String observaciones;
        private String observaciones1;
        private String observaciones2;
        private Date fechaActualizacionSaldoCliente;
        private String numeroComprobante;
        private String saldoACobrar;
        private String vuelto;
        private String empresa;
        private Integer numeroDeFletero;
        private String descripcionVehiculo;
        private String descripcionFletero;
        private String motivoFallido;
        private int marcadoParaReparto;
        private int marcadoParaProceso;
        private int marcadoComoAgregado;
        private int zonaAsignada;
        private int alertaAsignada;
        private int numeroVendedor;
        private Integer idPedidosTango;
        private Double cantidadOriginal;
        private int notificacion;
        private String descripcionSola;

    public String getDescripcionSola() {
        return descripcionSola;
    }

    public void setDescripcionSola(String descripcionSola) {
        this.descripcionSola = descripcionSola;
    }
        
        

    public int getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(int notificacion) {
        this.notificacion = notificacion;
    }
        
        

    public Double getCantidadOriginal() {
        return cantidadOriginal;
    }

    public void setCantidadOriginal(Double cantidadOriginal) {
        this.cantidadOriginal = cantidadOriginal;
    }
        

    public Integer getIdPedidosTango() {
        return idPedidosTango;
    }

    public void setIdPedidosTango(Integer idPedidosTango) {
        this.idPedidosTango = idPedidosTango;
    }
        

    public int getNumeroVendedor() {
        return numeroVendedor;
    }

    public void setNumeroVendedor(int numeroVendedor) {
        this.numeroVendedor = numeroVendedor;
    }
    public int getMarcadoComoAgregado() {
        return marcadoComoAgregado;
    }

    public void setMarcadoComoAgregado(int marcadoComoAgregado) {
        this.marcadoComoAgregado = marcadoComoAgregado;
    }
        

    public int getMarcadoParaReparto() {
        return marcadoParaReparto;
    }

    public void setMarcadoParaReparto(int marcadoParaReparto) {
        this.marcadoParaReparto = marcadoParaReparto;
    }

    public int getMarcadoParaProceso() {
        return marcadoParaProceso;
    }

    public void setMarcadoParaProceso(int marcadoParaProceso) {
        this.marcadoParaProceso = marcadoParaProceso;
    }
        

    public String getMotivoFallido() {
        return motivoFallido;
    }

    public void setMotivoFallido(String motivoFallido) {
        this.motivoFallido = motivoFallido;
    }
        

    public String getDescripcionFletero() {
        return descripcionFletero;
    }

    public void setDescripcionFletero(String descripcionFletero) {
        this.descripcionFletero = descripcionFletero;
    }

    public String getDescripcionVehiculo() {
        return descripcionVehiculo;
    }

    public void setDescripcionVehiculo(String descripcionVehiculo) {
        this.descripcionVehiculo = descripcionVehiculo;
    }
       

    public Integer getNumeroDeFletero() {
        return numeroDeFletero;
    }

    public void setNumeroDeFletero(Integer numeroDeFletero) {
        this.numeroDeFletero = numeroDeFletero;
    }
        
    public String getObservaciones1() {
        return observaciones1;
    }

    public void setObservaciones1(String observaciones1) {
        this.observaciones1 = observaciones1;
    }

    public String getObservaciones2() {
        return observaciones2;
    }

    public void setObservaciones2(String observaciones2) {
        this.observaciones2 = observaciones2;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
        

    public String getSaldoACobrar() {
        return saldoACobrar;
    }

    public void setSaldoACobrar(String saldoACobrar) {
        this.saldoACobrar = saldoACobrar;
    }

    public String getVuelto() {
        return vuelto;
    }

    public void setVuelto(String vuelto) {
        this.vuelto = vuelto;
    }
        

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

        
    public Date getFechaActualizacionSaldoCliente() {
        return fechaActualizacionSaldoCliente;
    }

    public void setFechaActualizacionSaldoCliente(Date fechaActualizacionSaldoCliente) {
        this.fechaActualizacionSaldoCliente = fechaActualizacionSaldoCliente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
        

        
    public String getFechaPedidosTango() {
        return fechaPedidosTango;
    }

    public void setFechaPedidosTango(String fechaPedidosTango) {
        this.fechaPedidosTango = fechaPedidosTango;
    }

        
        
    public Integer getNumeroDeHojaDeRuta() {
        return numeroDeHojaDeRuta;
    }

    public void setNumeroDeHojaDeRuta(Integer numeroDeHojaDeRuta) {
        this.numeroDeHojaDeRuta = numeroDeHojaDeRuta;
    }

    public Integer getNumeroDeListadoDeMateriales() {
        return numeroDeListadoDeMateriales;
    }

    public void setNumeroDeListadoDeMateriales(Integer numeroDeListadoDeMateriales) {
        this.numeroDeListadoDeMateriales = numeroDeListadoDeMateriales;
    }
        
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
        
    public Integer getCondicionEstadoDelPedido() {
        return condicionEstadoDelPedido;
    }

    public void setCondicionEstadoDelPedido(Integer condicionEstadoDelPedido) {
        this.condicionEstadoDelPedido = condicionEstadoDelPedido;
    }

    public Double getCantidadArticulosTotales() {
        return cantidadArticulosTotales;
    }

    public void setCantidadArticulosTotales(Double cantidadArticulosEntregados) {
        this.cantidadArticulosTotales = cantidadArticulosEntregados;
    }

        
    public Double getCantidadArticuloPendiente() {
        return cantidadArticuloPendiente;
    }

    public void setCantidadArticuloPendiente(Double cantidadArticuloPendiente) {
        this.cantidadArticuloPendiente = cantidadArticuloPendiente;
    }
   
    public Integer getCondicionDeVenta() {
        return condicionDeVenta;
    }

    public void setCondicionDeVenta(Integer condicionDeVenta) {
        this.condicionDeVenta = condicionDeVenta;
    }
        
    public Double getSaldoCliente() {
        return saldoCliente;
    }

    public void setSaldoCliente(Double saldoCliente) {
        this.saldoCliente = saldoCliente;
    }
        

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

        
    public Double getCantidadArticulo() {
        return cantidadArticulo;
    }

    public void setCantidadArticulo(Double cantidadArticulo) {
        this.cantidadArticulo = cantidadArticulo;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Double getPesoItems() {
        return pesoItems;
    }

    public void setPesoItems(Double pesoItems) {
        this.pesoItems = pesoItems;
    }
        

    public Integer getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(Integer codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public Connection getConeccionPedidos() {
        return coneccionPedidos;
    }

    public void setConeccionPedidos(Connection coneccionPedidos) {
        this.coneccionPedidos = coneccionPedidos;
    }

    public Boolean getConfirmacionPorceso() {
        return confirmacionPorceso;
    }

    public void setConfirmacionPorceso(Boolean confirmacionPorceso) {
        this.confirmacionPorceso = confirmacionPorceso;
    }

    public Integer getNumeroDeProceso() {
        return numeroDeProceso;
    }

    public void setNumeroDeProceso(Integer numeroDeProceso) {
        this.numeroDeProceso = numeroDeProceso;
    }
        
	public Boolean getEntregaCompletada() {
		return entregaCompletada;
	}

	public void setEntregaCompletada(Boolean entregaCompletada) {
		this.entregaCompletada = entregaCompletada;
	}
	
	public String getCodigoTangoDePedido() {
		return codigoTangoDePedido;
	}

	public void setCodigoTangoDePedido(String codigoTangoDePedido) {
		this.codigoTangoDePedido = codigoTangoDePedido;
	}

	public String getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Integer getiDPedido() {
		return iDPedido;
	}

	public void setiDPedido(Integer iDPedido) {
		this.iDPedido = iDPedido;
	}

	public Double getPesoTotal() {
		return pesoTotal;
	}

	public void setPesoTotal(Double pesoTotal) {
            //DecimalFormat dc=new DecimalFormat("####.#");
            //String peso=dc.format(pesoTotal);
            //this.pesoTotal=Double.parseDouble(peso);
            this.pesoTotal = pesoTotal;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Integer getVehiculoAsignado() {
		return vehiculoAsignado;
	}

	public void setVehiculoAsignado(Integer vehiculoAsignado) {
		this.vehiculoAsignado = vehiculoAsignado;
	}

    @Override
    public ArrayList buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList filtrar(String numeroCliente, String nombreCliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar(String cliente) {
        try {
            ArrayList listado=new ArrayList();
            //PedidosParaReparto ped=new PedidosParaReparto();
            Coneccion con=new Coneccion();
            Connection cnn=con.ObtenerConeccion();
            String sql="select pedidos_carga1.NRO_PEDIDO,pedidos_carga1.RAZON_SOC,pedidos_carga1.FEC_PEDIDO,pedidos_carga1.entrega,pedidos_carga1.listado,pedidos_carga1.hdr1,pedidos_carga1.vehiculo,pedidos_carga1.fletero,pedidos_carga1.orden_num,(select detalle_hdr.entregado from detalle_hdr where detalle_hdr.hdr=pedidos_carga1.hdr1 and detalle_hdr.cliente like pedidos_carga1.RAZON_SOC limit 0,1)as condicion,(select detalle_hdr.motivoFallido from detalle_hdr where detalle_hdr.cliente like '"+cliente+"%' and detalle_hdr.hdr=pedidos_carga1.hdr1 and detalle_hdr.motivoFallido not like 'null')as motivo,(select unidades.descripcion from unidades where unidades.numero = pedidos_carga1.vehiculo)as nombreV,(select fleteros.nombre from fleteros where fleteros.numero=pedidos_carga1.fletero)as nombreF,pedidos_carga1.zona,pedidos_carga1.alerta from pedidos_carga1 where RAZON_SOC like '"+cliente+"%' group by RAZON_SOC,hdr1";
            System.err.println(sql);
            Statement st=cnn.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while (rs.next()){
                System.out.println(rs.getString(1)+" nombre "+rs.getString(2));
                PedidosParaReparto ped=new PedidosParaReparto();
                ped.codigoTangoDePedido=rs.getString(1);
                ped.fechaPedidosTango=rs.getString(3);
                ped.razonSocial=rs.getString(2);
                ped.fechaEnvio=rs.getString(4);
                ped.numeroDeListadoDeMateriales=rs.getInt(5);
                ped.numeroDeHojaDeRuta=rs.getInt(6);
                ped.vehiculoAsignado=rs.getInt(7);
                ped.numeroDeFletero=rs.getInt(8);
                ped.numeroDeProceso=rs.getInt(9);
                Boolean entregado=true;
                String motivo=null;
                System.out.println("mot "+rs.getInt(10)+" desc "+rs.getString(11));
                if(rs.getInt(10)==1){
                    entregado=false;
                    motivo=rs.getString(11);
                }
                ped.entregaCompletada=entregado;
                ped.motivoFallido=motivo;
                ped.descripcionVehiculo=rs.getString(12);
                ped.descripcionFletero=rs.getString(13);
                ped.zonaAsignada=rs.getInt("zona");
                ped.alertaAsignada=rs.getInt("alerta");
                listado.add(ped);
            }
            rs.close();
            st.close();
            con.CerrarConneccion(cnn);
            return listado;
        } catch (SQLException ex) {
            Logger.getLogger(PedidosParaReparto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void marcarContactado(Integer item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificarDatosCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getZonaAsignada() {
        return zonaAsignada;
    }

    public void setZonaAsignada(int zonaAsignada) {
        this.zonaAsignada = zonaAsignada;
    }

    public int getAlertaAsignada() {
        return alertaAsignada;
    }

    public void setAlertaAsignada(int alertaAsignada) {
        this.alertaAsignada = alertaAsignada;
    }
	
}
