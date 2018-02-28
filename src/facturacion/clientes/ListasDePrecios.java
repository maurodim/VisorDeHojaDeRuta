/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.clientes;

/**
 *
 * @author hernan
 */
public class ListasDePrecios {
    private int numeroLista;
    private String descripcionLista;
    private Double porcentaje;

    public int getNumeroLista() {
        return numeroLista;
    }

    public String getDescripcionLista() {
        return descripcionLista;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public ListasDePrecios() {
        this.numeroLista = 0;
        this.descripcionLista = "";
        this.porcentaje = 0.00;
    }
    
    
}
