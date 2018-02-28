/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.tablas;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hernan
 */
public class TablaExportacion extends DefaultTableModel{
    @Override
       public Class getColumnClass(int colum){
        //if(colum==4)return Boolean.class;
        if(colum==1) {
               return Boolean.class;
           }
        if(colum==2) {
               return Boolean.class;
           }
        if(colum==3) {
               return Boolean.class;
           }
        if(colum==5) {
               return Double.class;
           }
        if(colum==8) {
               return Boolean.class;
           }
        if(colum==9) {
               return Boolean.class;
           }
        if(colum==10) {
               return Double.class;
           }
        return String.class;
    }
       public boolean isCellEditable(int fila,int colum){
        if(getValueAt(fila,9).equals(Boolean.TRUE)) {
               return false;
           }
        
           return true;        
       }
    
}
