/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;


import interfacesPrograma.Facturar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import visordehojaderuta.Coneccion;
//import proceso.Coneccion;

/**
 *
 * @author Administrador
 */
public class Articulos implements Facturar{
    private String codigo;
    private Double pesoUnitario;
    private static ArrayList codigoL=new ArrayList();
    private static ArrayList pesoUnitarioL=new ArrayList();
    static Connection cart=Coneccion.ObtenerConeccion();
    private String descripcionArticulo;
    private String sinonimoArticulo;
    private int estado;
    private Double cantidad;
    private Double precioUnitario;
    private String unidadDeMedida;
    private Double precioTotal;
    

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }
    

    public static Connection getCart() {
        return cart;
    }

    public static void setCart(Connection cart) {
        Articulos.cart = cart;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public String getSinonimoArticulo() {
        return sinonimoArticulo;
    }

    public void setSinonimoArticulo(String sinonimoArticulo) {
        this.sinonimoArticulo = sinonimoArticulo;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo=codigo+"      ";
    }

    public Double getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(Double pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }
    public synchronized void cargarListado() throws SQLException, InterruptedException{
    try{
        String codig;
        String sql="select * from PESOS";
        Statement st=cart.createStatement();
        st.execute(sql);
        ResultSet rs=st.getResultSet();
        while(rs.next()){
            codig=rs.getString("codigo");
            codig.trim();
            codigoL.add(codig);
            pesoUnitarioL.add(rs.getDouble("peso"));
        }
        rs.close();
    }catch(Exception ex){
        //Thread.sleep(2000);
        String codig;
        String sql="select * from PESOS";
        Statement st=cart.createStatement();
        st.execute(sql);
        ResultSet rs=st.getResultSet();
        while(rs.next()){
            codig=rs.getString("codigo");
            codig.trim();
            codigoL.add(codig);
            pesoUnitarioL.add(rs.getDouble("peso"));
        }
        rs.close();
        
    }
    }

    public static ArrayList getCodigoL() {
        return codigoL;
    }

    public static ArrayList getPesoUnitarioL() {
        return pesoUnitarioL;
    }
    public Integer ubicarPosicion(String cod){
        Integer cantidad;
        Integer posicion = 1;
        String compar;
        cantidad=codigoL.size();
        System.out.println("ubicarPosicion.. cantidad:"+cantidad+" string:"+cod);
        for(Integer i=0;i<cantidad;i++){
            compar=(String) codigoL.get(i);
            compar.trim();
            compar+="      ";
            cod.trim();
            System.out.println(compar.length()+" "+compar+" "+cod.length()+" "+cod);
            if(compar.equals(cod)){
                posicion=i;

        }
       
    }
         return posicion;
    }
    public void cargarObjetoSeleccionado(Integer posicion){
        System.out.println("cargarObjetoSeleccionado recibe :"+posicion);
        this.codigo=(String) codigoL.get(posicion);
        this.pesoUnitario=(Double) pesoUnitarioL.get(posicion);
        
    }

    @Override
    public Boolean guardar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean nuevo(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leer(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imprimirComprobante(int tipoComprobante, Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listadoBusqueda(String criterio) {
        ArrayList listado=new ArrayList();
        try {
            Coneccion con=new Coneccion();
            Articulos art=null;
            Connection cnn=Coneccion.ObtenerConeccion();
            String sql="select *,(select listadeprecios1.PRE_FACTU from listadeprecios1 where listadeprecios1.COD_ARTIC = articulosv.CodArticulo)as precio,(select listadeprecios1.UNIDAD_MED from listadeprecios1 where listadeprecios1.COD_ARTIC = articulosv.CodArticulo)as medida from articulosv where Sinonimo like '"+criterio+"%'";
            Statement st=cnn.createStatement();
            st.execute(sql);
            ResultSet rs=st.getResultSet();
            while(rs.next()){
                art=new Articulos();
                art.setCodigo(rs.getString("CodArticulo"));
                art.setDescripcionArticulo(rs.getString("Descripcion"));
                art.setPesoUnitario(rs.getDouble("peso"));
                art.setPrecioUnitario(rs.getDouble("precio"));
                art.setUnidadDeMedida(rs.getString("medida"));
                listado.add(art);
                
            }
            st.close();
            Coneccion.CerrarConneccion(cnn);
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
        
    }
}
