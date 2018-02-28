/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.procesosDelExportadorDePedidos;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author mauro
 */
public interface Editable {
    public Integer agregar(Object objeto);
    public void modificacion(Object objeto);
    public ArrayList listar();
    public DefaultComboBoxModel mostrarEnCombo(ArrayList listado);
}
