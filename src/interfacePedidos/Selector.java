/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos;

import interfacePedidos.objetosExportacion.PedidoGuardado;
import interfacePedidos.objetosExportacion.Vendedor;
import interfacePedidos.procesosDelExportadorDePedidos.Editable;
import interfacePedidos.procesosDelExportadorDePedidos.ExportacionDePedidos;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.ConeccionSqlTango;
import objetos.Pedidos;
import visordehojaderuta.InicioVisorDeHojaDeRuta;

/**
 *
 * @author mauro
 */
public class Selector extends javax.swing.JInternalFrame {
    private Editable edita;
    private Vendedor vendedor;
    private String empresa;
    private ArrayList listadoVendedores;
    private ArrayList listadoPedidos;
    private String fechaSeleccionada;
    private Connection conSql;
    
    /**
     * Creates new form Selector
     */
    public Selector() {
        initComponents();
        ConeccionSqlTango conec=new ConeccionSqlTango(5);
        edita=new Vendedor();
        vendedor=new Vendedor();
        empresa="";
        listadoVendedores=new ArrayList();
        listadoPedidos=new ArrayList();
        listadoVendedores=edita.listar();
        this.jComboBox1.setModel(edita.mostrarEnCombo(listadoVendedores));
        this.jPanel2.setVisible(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setTitle("Selector de Vendedor");

        jLabel1.setText("Seleccionar Vendedor");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Seleccionar Empresa");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BU3", "SIDERCON", "SRL" }));

        jLabel3.setText("Seleccionar Fecha Pedido en Tango");

        jButton1.setText("Siguiente >>>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(248, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Siguiente >>>>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int posicion=this.jComboBox1.getSelectedIndex();
        vendedor=(Vendedor) listadoVendedores.get(posicion);
        int idEmpresa=this.jComboBox2.getSelectedIndex();
        empresa="";
        switch(idEmpresa){
            case 0:
                empresa="BU3";
                idEmpresa=1;
                break;
            case 1:
                empresa="SIDERCON";
                idEmpresa=2;
                break;
            case 2:
                empresa="SRL";
                idEmpresa=3;
                break;
            default:
                empresa="BU3";
                break;
    }
        try {
            //idEmpresa=5;//  A QUITAR ESTO
            //conSql=(Connection) new ConeccionSqlTango(5);
            conSql=(Connection) ConeccionSqlTango.ObtenerConeccion(idEmpresa);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Selector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fechaSeleccionada=leerChooser();
        this.jPanel2.setVisible(true);
        //tengo que mostrar en el jtable el resultado de pedidos para su seleccion, agrupando por numero de pedido
        //ArrayList listadoT=new ArrayList();
        ExportacionDePedidos expor=new PedidoGuardado();
        listadoPedidos=expor.leerTango(vendedor.getId_tango(),fechaSeleccionada, conSql);
        this.jTable1.setModel(expor.mostrarEnTabla(listadoPedidos));
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ArrayList seleccion=new ArrayList();
        int posicion=this.jTable1.getSelectedRow();
        Pedidos pedid=new Pedidos();
        ExportacionDePedidos expo=new PedidoGuardado();
        pedid=(Pedidos) listadoPedidos.get(posicion);
        seleccion=expo.leerDetalleTango(vendedor.getId_tango(),fechaSeleccionada, conSql,pedid.getNumeroPedidos());
        System.out.println("CANTIDAD DE DETALLE PEDIDO: "+seleccion.size());
        EditorDePedidos editor=new EditorDePedidos(seleccion);
        InicioVisorDeHojaDeRuta.jDesktopPane1.add(editor);
        editor.setVisible(true);
        editor.pack();
        editor.setTitle("EDITOR DE PEDIDOS VENDEDOR: "+vendedor.getNombre()+" EMPRESA: "+empresa+" FECHA PEDIDO: "+fechaSeleccionada);
        editor.toFront();
    }//GEN-LAST:event_jButton2ActionPerformed
private String leerChooser(){
     DecimalFormat fr=new DecimalFormat("00");
        DecimalFormat formato=new DecimalFormat("####.##");
        //SiderconCapaatos.listaPedidos.clear();
        SimpleDateFormat dia=new SimpleDateFormat("dd/mm/yyyy");
        //Date mes=Calendar.getInstance().getTime();
        //dateChooserCombo1.setDateFormat(dia);
        Calendar fechaNueva=dateChooserCombo1.getSelectedDate();
        //mes=dia.format(fechaNueva,null,null);
        Double pesoDia=0.00;
        int ano=fechaNueva.get(Calendar.YEAR);
        int mes=fechaNueva.get(Calendar.MONTH);
        mes++;
        int dd=fechaNueva.get(Calendar.DAY_OF_MONTH);
        
        //String fechaNueva=dateChooserCombo1.getText();
        //String fechaNueva=dateChooserCombo1.getSelectedDate();
        //String seleccion1=fechaNueva.toString();
        String seleccion=ano+"-"+fr.format(mes)+"-"+fr.format(dd);
        //seleccion=new SimpleDateFormat(seleccion1).format(mes);
        return seleccion;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}