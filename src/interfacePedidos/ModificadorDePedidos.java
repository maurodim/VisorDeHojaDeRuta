/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePedidos;

import interfacePedidos.objetosExportacion.PedidoParaExportar;
import interfacePedidos.objetosExportacion.Vendedor;
import interfacePedidos.procesosDelExportadorDePedidos.ExportacionDePedidos;
import interfacePedidos.procesosDelExportadorDePedidos.Ruteable;
import interfacePedidos.tablas.MiTablaDetallePedidos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import objetos.Mensajeria.Corregible;
import objetos.Mensajeria.Mensajes;
import objetos.Mensajeria.Rutas;
import objetos.Pedidos;
import parametros.Parametrizar;
import visordehojaderuta.VisorDeHojaDeRuta;

/**
 *
 * @author hernan
 */
public class ModificadorDePedidos extends javax.swing.JInternalFrame {

    /**
     * Creates new form ModificadorDePedidos
     */
    Vendedor vend=new Vendedor();
    static ArrayList listadoPedidos=new ArrayList();
    static ArrayList listadoDetallePedidos=new ArrayList();
    Pedidos pd=new Pedidos();
    PedidoParaExportar pdExp=new PedidoParaExportar();
    
    public ModificadorDePedidos(Object ven) {
        vend=(Vendedor)ven;
        initComponents();
        cargarList();
    }
private void cargarList(){
    Pedidos ped=new Pedidos();
//ExportacionDePedidos exp=new Pedidos();
//ArrayList listado=new ArrayList();
ped.setCodigoVendedor(vend.getNumero());
listadoPedidos=ped.listarP();
DefaultListModel lista=new DefaultListModel();
Iterator il=listadoPedidos.listIterator();
while(il.hasNext()){
    ped=(Pedidos)il.next();
    StringBuffer razSocial=new StringBuffer(30);
    razSocial.insert(0,ped.getRazonSocial());
    //razSocial=StringBufferped.getRazonSocial();
    razSocial.setLength(30);
    lista.addElement(ped.getNumeroPedidos()+" "+razSocial+" FECHA DE ENTREGA : "+ped.getFechaEntrega());
    System.out.println("pedidos "+ped.getNumeroPedidos()+" raz social "+razSocial);
    this.jPanel2.setVisible(false);
    this.jPanel3.setVisible(false);
}
this.jList1.setModel(lista);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MiTablaDetallePedidos detallePedidos=new MiTablaDetallePedidos();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Editor de Pedidos pasados a reparto s/ LPM generada");

        jLabel1.setText("Pedidos pasados a reparto\n S/LPM generada");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jScrollPane1.setViewportView(jList1);

        jButton2.setText("CARGAR DETALLE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(31, 31, 31))))
        );

        jPanel2.setBackground(new java.awt.Color(94, 141, 97));

        jTable1.setModel(detallePedidos);
        detallePedidos.addColumn("N° PEDIDO");
        detallePedidos.addColumn("COD ARTICULO");
        detallePedidos.addColumn("CANTIDAD");
        detallePedidos.addColumn("FECHA ASIGNADA");
        detallePedidos.addColumn("ZONA ASIGNADA");
        detallePedidos.addColumn("DESCRIPCION");
        detallePedidos.addColumn("ELIMINAR");
        Object[] fila=new Object[7];
        jScrollPane2.setViewportView(jTable1);

        jLabel2.setText("N° de Pedido :");

        jLabel3.setText("Razon Social :");

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        jLabel6.setText("Fecha De Entrega");

        jLabel7.setText("Zona Asignada :");

        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox1FocusLost(evt);
            }
        });

        jButton3.setText("GUARDAR ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(105, 142, 108));
        jCheckBox1.setText("Modificar Zona");
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });

        jCheckBox2.setBackground(new java.awt.Color(91, 146, 95));
        jCheckBox2.setText("Renglon 1");
        jCheckBox2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox2StateChanged(evt);
            }
        });

        jCheckBox3.setBackground(new java.awt.Color(91, 146, 95));
        jCheckBox3.setText("Renglon 2");
        jCheckBox3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox3StateChanged(evt);
            }
        });

        jCheckBox4.setBackground(new java.awt.Color(91, 146, 95));
        jCheckBox4.setText("Renglon 3");
        jCheckBox4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox4StateChanged(evt);
            }
        });

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextField1FocusLost(evt);
            }
        });

        jLabel8.setText("Observaciones :");

        jCheckBox5.setBackground(new java.awt.Color(50, 94, 56));
        jCheckBox5.setText("Abrir Zona");
        jCheckBox5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox5StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox4)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 53, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(jCheckBox5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jButton3)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int numero=this.jList1.getSelectedIndex();
       //PedidoParaExportar pedE=new PedidoParaExportar();
       listadoDetallePedidos.clear();
       Pedidos ped=new PedidoParaExportar();
       PedidoParaExportar pEx=(PedidoParaExportar)ped;
       ped=(Pedidos)listadoPedidos.get(numero);
       pd=ped;
       listadoDetallePedidos=pEx.listarPed(ped.getNumeroPedidos());
       //MaskFormatter mascara=new MaskFormatter();
       ArrayList listadoRutas=new ArrayList();
       this.jComboBox1.removeAllItems();
       Rutas rtt=new Rutas();
       Ruteable rut=new Rutas();
       listadoRutas=rut.ListarPredefinidas(ped.getFechaEntrega());
       Iterator ilr=listadoRutas.listIterator();
       while(ilr.hasNext()){
           rtt=(Rutas)ilr.next();
           System.err.println("RUTA CARGADA "+rtt.getDescripcionRuta());
           this.jComboBox1.addItem(rtt.getDescripcionRuta());
       }
       //String codigo=
       String codigo=pEx.getCodigoArticulo();
       System.err.println(" cantidad en array "+listadoDetallePedidos.size()+" codigo "+codigo);
       this.jPanel2.setVisible(true);
       this.jPanel3.setVisible(true);
       this.jComboBox1.setVisible(false);
       this.jCheckBox5.setVisible(false);
       this.jLabel4.setText(ped.getNumeroPedidos());
       this.jLabel5.setText(ped.getRazonSocial());
       this.jLabel7.setText("Zona Asignada :"+ped.getDescripcionZona());
       //this.jTextField1.setText(ped.getFechaEntrega());
       this.jFormattedTextField1.setValue(ped.getFechaEntrega());
       this.jFormattedTextField1.requestFocus();
       //this.jTextField1.requestFocus();
       PedidoParaExportar pedExp=new PedidoParaExportar();
       jTable1.removeAll();
       MiTablaDetallePedidos detalleP=new MiTablaDetallePedidos();
       jTable1.setModel(detalleP);
       detalleP.addColumn("Cod Articulo");
       detalleP.addColumn("Desc. Articulo");
       detalleP.addColumn("Cantidad");
       detalleP.addColumn("Cantidad Pendiente");
       detalleP.addColumn("Fecha Asignada");
       detalleP.addColumn("Zona");
       detalleP.addColumn("Eliminar");
       Object[] fila=new Object[7];
       Iterator ilDet=listadoDetallePedidos.listIterator();
       while(ilDet.hasNext()){
           pedExp=(PedidoParaExportar)ilDet.next();
           fila[0]=pedExp.getCodigoArticulo();
           fila[1]=pedExp.getDescripcionArticulo();
           fila[2]=pedExp.getCantidadPedido();
           fila[3]=pedExp.getCantidadPendiente();
           fila[4]=pedExp.getFechaEntrega();
           fila[5]=ped.getDescripcionZona();
           fila[6]=false;
           detalleP.addRow(fila);
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
        if(jCheckBox1.isSelected()){
        this.jComboBox1.setVisible(true);
               ArrayList listadoRutas=new ArrayList();
       this.jComboBox1.removeAllItems();
       Rutas rtt=new Rutas();
       Ruteable rut=new Rutas();
       listadoRutas=rut.ListarPredefinidas(jFormattedTextField1.getText());
       String fechaNueva=jFormattedTextField1.getText();
       Iterator ilr=listadoRutas.listIterator();
       while(ilr.hasNext()){
           rtt=(Rutas)ilr.next();
           this.jComboBox1.addItem(rtt.getDescripcionRuta());
       }

        this.jCheckBox5.setVisible(true);
        }else{
            this.jComboBox1.setVisible(false);
            this.jCheckBox5.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jCheckBox2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox2StateChanged
        if(jCheckBox2.isSelected()){
            String leyenda1=JOptionPane.showInputDialog("Modifique las observaciones del primer renglon",pd.getLeyenda1());
            pd.setLeyenda1(leyenda1);
        }
    }//GEN-LAST:event_jCheckBox2StateChanged

    private void jCheckBox3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox3StateChanged
       if(jCheckBox3.isSelected()){
            String leyenda2=JOptionPane.showInputDialog("Modifique las observaciones del segundo renglon",pd.getLeyenda2());
            pd.setLeyenda2(leyenda2);
        }
    }//GEN-LAST:event_jCheckBox3StateChanged

    private void jCheckBox4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox4StateChanged
       if(jCheckBox4.isSelected()){
            String leyenda3=JOptionPane.showInputDialog("Modifique las observaciones del tercer renglon",pd.getLeyenda3());
            pd.setLeyenda3(leyenda3);
        }
    }//GEN-LAST:event_jCheckBox4StateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ExportacionDePedidos expP=new Pedidos();
        String fch=this.jFormattedTextField1.getText();
        pd.setFechaEntrega(fch);
        if(this.jCheckBox1.isSelected()){
            pd.setNumeroZona(this.jComboBox1.getSelectedIndex());
        }
        if(expP.modificar(pd)){
            
            Mensajes men=new Mensajes();
            Parametrizar par=new Mensajes();
            
            men.setEquipoOrigen(VisorDeHojaDeRuta.tG.getNumeroEquipo());
            men.setEstadoMensaje(1);
            men.setTemaMensaje(4);
            men.setEquipoDestino(1);
            men.setTexto("PEDIDO DE "+pd.getRazonSocial()+" NUMERO :"+pd.getNumeroPedidos()+" FUE MODIFICADO");
            if(par.enviarMensaje(men)){
              JOptionPane.showMessageDialog(null,"PEDIDO MODIFICADO CON EXITO"); 
            }
        }else{
            JOptionPane.showMessageDialog(null,"EL PEDIDO NO HA SIDO MODIFICADO, COMUNIQUESE CON EL ADMINISTRADOR PARA NOTIFICAR LOS MOTIVOS");
        }
        this.jPanel2.setVisible(false);
        this.jPanel3.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jFormattedTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusLost
       ArrayList listadoRutas=new ArrayList();
       this.jComboBox1.removeAllItems();
       Rutas rtt=new Rutas();
       Ruteable rut=new Rutas();
       listadoRutas=rut.ListarPredefinidas(jFormattedTextField1.getText());
       String fechaNueva=jFormattedTextField1.getText();
       Iterator ilr=listadoRutas.listIterator();
       while(ilr.hasNext()){
           rtt=(Rutas)ilr.next();
           this.jComboBox1.addItem(rtt.getDescripcionRuta());
       }
       Iterator ilp=listadoDetallePedidos.listIterator();
       int i=0;
       while(ilp.hasNext()){
           PedidoParaExportar pedd=(PedidoParaExportar)ilp.next();
           pedd.setFechaEntrega(fechaNueva);
           this.jTable1.setValueAt(fechaNueva, i, 4);
           i++;
       }

    }//GEN-LAST:event_jFormattedTextField1FocusLost

    private void jCheckBox5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox5StateChanged
       if(jCheckBox5.isSelected()){
        Ruteable ruta=new Rutas();
        ArrayList rutas=new ArrayList();
        Rutas rtas;
        rutas=ruta.ListarRutas();
        Iterator ilr=rutas.listIterator();
        Object[] ras=new Object[rutas.size()];
        int item=0;
        while(ilr.hasNext()){
            rtas=(Rutas)ilr.next();
            ras[item]=rtas.getDescripcionRuta();
            item++;
        }
           //new Object[]   
        Object seleccion=JOptionPane.showInputDialog(this,"Seleccione Ruta a Abrir :","Selector de Opciones",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"SANTO TOME/SAUCE VIEJO","RECREO/MONTE VERA","LA COSTA/RINCON/ARROYO LEYES"}, "SANTO TOME/SAUCE VIEJO");
        Mensajes men=new Mensajes();
        men.setEquipoOrigen(VisorDeHojaDeRuta.tG.getNumeroEquipo());
        men.setEquipoDestino(1);
        men.setEstadoMensaje(1);
        men.setTemaMensaje(3);
        men.setTexto("SE ESTA INTENTADO ABRIR UNA RUTA NO ESTABLECIDA, DESTINO :"+seleccion);
        Parametrizar par=new Mensajes();
        par.enviarMensaje(men);
       }
    }//GEN-LAST:event_jCheckBox5StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       int cantidad=this.jTable1.getRowCount();
       Corregible cor=new PedidoParaExportar();
       System.out.println("CANTIDAD ITERACIONES "+cantidad);
       Double cant=0.00;
       for(int i=0;i <= cantidad;i++){
           PedidoParaExportar ped=(PedidoParaExportar)listadoDetallePedidos.get(i);
           cant=(Double) this.jTable1.getValueAt(i,2);
           ped=(PedidoParaExportar)cor.ModificarCantidad(ped,cant);
           this.jTable1.setValueAt(ped.getCantidadPedido(), i,2);
           this.jTable1.setValueAt(ped.getCantidadPendiente(), i,3);
           
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusLost
        String nombreRuta=(String) this.jComboBox1.getSelectedItem();
        Rutas rt=new Rutas();
        int numeroR=1;
        try {
            numeroR=rt.leerNumeroDeRuta(nombreRuta);
        } catch (SQLException ex) {
            Logger.getLogger(ModificadorDePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("SELECCIONADO RUTA "+nombreRuta+" numero "+numeroR);
        int cant=this.jTable1.getRowCount();
        for(int aa=0;aa <= cant;aa++){
            this.jTable1.setValueAt(nombreRuta,aa,5);
        }
        
    }//GEN-LAST:event_jComboBox1FocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}