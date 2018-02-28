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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import visordehojaderuta.Coneccion;



/**
 *
 * @author USUARIO
 */
public class Vehiculos implements Busquedas{
        private static ArrayList ListadoVehiculos=new ArrayList();
	private Integer numero;
	private String descripcion;
	private Double capacidadDeCarga;
        private Integer kilometrosInicio;
        private Integer kilometrosFinales;
        private Integer kilometrosRecorridos;
        private Double kilosCargados;
        private String patente;
        private Integer kilometrosActuales;
        private String estadoVehiculo;
        private Integer numeroHdr;
        private int condicion;//CONDICION CORRESPONDE A UNA BANDERA PARA SABER SI SE ELIMINA O NO

    public Integer getNumeroHdr() {
        return numeroHdr;
    }

    public void setNumeroHdr(Integer numeroHdr) {
        this.numeroHdr = numeroHdr;
    }

    public int getCondicion() {
        return condicion;
    }

    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }

        
    public String getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(int estadoVehiculo) {
        String estado="";
        switch (estadoVehiculo){
            case 1:
                estado="EN PROCESO DE CARGA";
                        break;
            case 2:
                estado="CON CARGA COMPLETA";
                break;
            case 3:
                estado="EN REPARTO";
                break;
            case 4:
                estado="CON RECORRIDO FINALIZADO";
                break;
        }
        this.estadoVehiculo = estado;
    }
  
    public Integer getKilometrosActuales() {
        return kilometrosActuales;
    }

    public void setKilometrosActuales(Integer kilometrosActuales) {
        this.kilometrosActuales = kilometrosActuales;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }
        

    public Integer getKilometrosFinales() {
        return kilometrosFinales;
    }

    public void setKilometrosFinales(Integer kilometrosFinales) {
        this.kilometrosFinales = kilometrosFinales;
    }

    public Integer getKilometrosInicio() {
        return kilometrosInicio;
    }

    public void setKilometrosInicio(Integer kilometrosInicio) {
        this.kilometrosInicio = kilometrosInicio;
    }

    public Integer getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }

    public void setKilometrosRecorridos(Integer kilometrosRecorridos) {
        this.kilometrosRecorridos = kilometrosRecorridos;
    }

    public Double getKilosCargados() {
        return kilosCargados;
    }

    public void setKilosCargados(Double kilosCargados) {
        this.kilosCargados = kilosCargados;
    }
        

	public Double getCapacidadDeCarga() {
		return capacidadDeCarga;
	}

	public void setCapacidadDeCarga(Double capacidadDeCarga) {
		this.capacidadDeCarga = capacidadDeCarga;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public void modificarCapacidadDeCarga(Double peso){
            this.capacidadDeCarga=this.capacidadDeCarga-peso;
        }
        public void modificarKilosCargados(Double peso){
            this.kilosCargados+=peso;
            if((this.kilosCargados == this.capacidadDeCarga)||(this.kilosCargados > this.capacidadDeCarga)){
                //JOptionPane.showMessageDialog(null,"SALDO DEL CLIENTE : $"+resultado+" cliente numero :"+ped.getCodigoCliente(),"SALDO DEL CLIENTE ",JOptionPane.PLAIN_MESSAGE);
                this.setEstadoVehiculo(2);
                this.enviarMensajeDeEstadoDelVehiculo();
            }
            
        }
        public void modificarKilometrosActual(Integer km){
            this.kilometrosActuales=km;
        }
        public void cargarKilometrosIniciales(Integer km){
            this.kilometrosInicio=km;
        }
        public void cargarKilometrosFinales(Integer km1){
            this.kilometrosFinales=km1;
        }
        public void calcularKilometrosRecorridos(){
            this.kilometrosRecorridos=this.kilometrosFinales - this.kilometrosInicio;
            this.kilometrosActuales+=this.kilometrosRecorridos;
        }
        private void enviarMensajeDeEstadoDelVehiculo(){
            
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
            Coneccion con=new Coneccion();
            Connection cnn=con.ObtenerConeccion();
            String sql="select * from unidades order by numero";
            Statement st=cnn.createStatement();
            st.execute(sql);
            ArrayList listado=new ArrayList();
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                Vehiculos ve=new Vehiculos();
                ve.capacidadDeCarga=rs.getDouble("carga_max");
                ve.descripcion=rs.getString("descripcion");
                ve.kilometrosActuales=rs.getInt("kilometrosActuales");
                listado.add(ve);
            }
            rs.close();
            st.close();
            con.CerrarConneccion(cnn);
            return listado;
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
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
        
}
