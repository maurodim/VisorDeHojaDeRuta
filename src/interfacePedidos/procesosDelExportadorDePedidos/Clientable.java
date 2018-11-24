/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.procesosDelExportadorDePedidos;

import java.sql.Connection;

/**
 *
 * @author mauro
 */
public interface Clientable {
    public Object Cargar(Connection bdT, String codigoC);
    public Object Actualizar(Connection bdT, String codigoC);
}
