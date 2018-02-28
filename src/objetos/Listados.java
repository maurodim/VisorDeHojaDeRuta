/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.Date;

/**
 *
 * @author Administrador
 */
public class Listados {
        //private String titulo;
        private Date fechaDeImpresion;
        private Integer numeroPedidoParaReparto;
        //private String codigoArticulo;
        //private String descripcionArticulo;
        //private Double cantidadArticulo;
        //private String detalleFraccion;
        //private Double pesoArticulo;
        private String observacionesPedido;
        private Integer numeroListado;
        private Integer numeroRevision;
        private Date fechaDeEntrega;

    public Date getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(Date fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public Date getFechaDeImpresion() {
        return fechaDeImpresion;
    }

    public void setFechaDeImpresion(Date fechaDeImpresion) {
        this.fechaDeImpresion = fechaDeImpresion;
    }

    public Integer getNumeroListado() {
        return numeroListado;
    }

    public void setNumeroListado(Integer numeroListado) {
        this.numeroListado = numeroListado;
    }

    public Integer getNumeroPedidoParaReparto() {
        return numeroPedidoParaReparto;
    }

    public void setNumeroPedidoParaReparto(Integer numeroPedidoParaReparto) {
        this.numeroPedidoParaReparto = numeroPedidoParaReparto;
    }

    public Integer getNumeroRevision() {
        return numeroRevision;
    }

    public void setNumeroRevision(Integer numeroRevision) {
        this.numeroRevision = numeroRevision;
    }

    public String getObservacionesPedido() {
        return observacionesPedido;
    }

    public void setObservacionesPedido(String observacionesPedido) {
        this.observacionesPedido = observacionesPedido;
    }
        
}
