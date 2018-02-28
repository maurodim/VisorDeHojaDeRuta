/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos.tablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author hernan
 */
public class MiRender extends DefaultTableCellRenderer
{
   public Component getTableCellRendererComponent(JTable table,
      int value,
      boolean isSelected,
      boolean hasFocus,
      int row,
      int column)
   {
      super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
      if (value==2)
      {
         this.setOpaque(true);
         this.setBackground(Color.RED);
         this.setForeground(Color.YELLOW);
      }
      return this;
   }
} 
    

