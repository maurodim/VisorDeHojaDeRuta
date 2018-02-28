/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import interfaces.clientes.CargasHdr;
import interfaces.clientes.ModificacionDePedidos;
import interfacesPrograma.Busquedas;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import facturacion.clientes.ClientesTango;
import objetos.PedidosParaReparto;
import tablas.MiModeloTablaBuscarCliente;
import visordehojaderuta.InicioVisorDeHojaDeRuta;

/**
 *
 * @author Administrador
 */
public class BusquedaDeCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form BusquedaDeCliente
     */
    private ArrayList resultado=new ArrayList();
    int posicionListado=0;
    private ArrayList listadoPedidos=new ArrayList();
    
    public BusquedaDeCliente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Buscador de Clientes");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setText("Ingrese Razon Social Cliente :");

        jButton1.setText("Ejecutar Busqueda");
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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );

        MiModeloTablaBuscarCliente busC=new MiModeloTablaBuscarCliente();
        jTable2.setModel(busC);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList1);

        jButton2.setText("CARGAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jButton2)
                        .addGap(0, 70, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        this.jPanel5.setVisible(false);
        this.jPanel2.setVisible(false);
    }//GEN-LAST:event_formComponentShown

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nombre=jTextField1.getText();
        ClientesTango resCli=new ClientesTango();
        Busquedas mcli=new ClientesTango();
        resultado.clear();
        //ArrayList resultado=new ArrayList();
        resultado=mcli.listar(nombre.toUpperCase());
        int cant=resultado.size();
        Iterator ir=resultado.listIterator();
        this.jPanel5.setVisible(true);
        DefaultListModel modelo=new DefaultListModel();
        //StringBuffer nomb=new StringBuffer();
        //nomb.setLength(50);
        
        while(ir.hasNext()){
            resCli=(ClientesTango)ir.next();
            System.err.println(cant+"NOMBRE "+resCli.getRazonSocial());
            //nomb.delete(0,50);
            //nomb.insert(0,resCli.getRazonSocial());
            modelo.addElement(resCli.getRazonSocial()+" ");
        }
        this.jList1.setModel(modelo);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ClientesTango rCli=new ClientesTango();
        listadoPedidos.clear();
        rCli=(ClientesTango)resultado.get(posicionListado);
        String nombreCliente=rCli.getRazonSocial();
        String codigoCliente=rCli.getCodigoCliente();
        System.out.println(nombreCliente);
        Busquedas pedidosB=new PedidosParaReparto();
        listadoPedidos=pedidosB.listar(nombreCliente.trim());
        this.jPanel2.setVisible(true);
        CargarTabla();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        posicionListado=jList1.locationToIndex(evt.getPoint());
    }//GEN-LAST:event_jList1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int fila=jTable2.rowAtPoint(evt.getPoint());
        Object seleccion=JOptionPane.showInputDialog(this,"Selecciones Opcion :","Selector de Opciones",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"detalle de pedido","hoja de ruta"}, "detalle de pedido");
        System.out.println("eligio "+seleccion );
        String sel=(String)seleccion;
        if(sel.equals("detalle de pedido")){
            PedidosParaReparto ped=new PedidosParaReparto();
            //fila++;
            ped=(PedidosParaReparto)listadoPedidos.get(fila);
            //CargaHdr carga=new CargaHdr();
            ModificacionDePedidos mod=new ModificacionDePedidos(ped);
            mod.setPedido(ped);
            mod.setNmPedido(ped.getCodigoTangoDePedido());
            System.out.println("pedido enviado "+ped.getCodigoTangoDePedido());
            InicioVisorDeHojaDeRuta.jDesktopPane1.add(mod);
            mod.setVisible(true);
            mod.toFront();
         }else{
            int hdr=(Integer)jTable2.getValueAt(fila, 4);
            System.out.println("hdr numero "+hdr);
            CargasHdr carga=new CargasHdr(hdr);
            InicioVisorDeHojaDeRuta.jDesktopPane1.add(carga);
            carga.setVisible(true);
            carga.toFront();
            
        }
    }//GEN-LAST:event_jTable2MouseClicked
    private void CargarTabla(){
        MiModeloTablaBuscarCliente busC=new MiModeloTablaBuscarCliente();
        this.jTable2.removeAll();
        //ArrayList listadoPedidos=new ArrayList();
        this.jTable2.setModel(busC);
        PedidosParaReparto pedidos=new PedidosParaReparto();
        busC.addColumn("NRO PEDIDO");
        busC.addColumn("F PEDIDO");
        busC.addColumn("F ENTREGA");
        busC.addColumn("Nº LISTADO");
        busC.addColumn("Nº HDR");
        busC.addColumn("VEHICULO");
        busC.addColumn("FLETERO");
        busC.addColumn("OT");
        busC.addColumn("ENTREGA OK?");
        busC.addColumn("MOTIVO");
        Object[] fila=new Object[10];
        Iterator irP=listadoPedidos.listIterator();
        while(irP.hasNext()){
            pedidos=(PedidosParaReparto) irP.next();
            fila[0]=pedidos.getCodigoTangoDePedido();
            fila[1]=pedidos.getFechaPedidosTango();
            fila[2]=pedidos.getFechaEnvio();
            fila[3]=pedidos.getNumeroDeListadoDeMateriales();
            fila[4]=pedidos.getNumeroDeHojaDeRuta();
            fila[5]=pedidos.getDescripcionVehiculo();
            fila[6]=pedidos.getDescripcionFletero();
            fila[7]=pedidos.getNumeroDeProceso();
            fila[8]=pedidos.getEntregaCompletada();
            fila[9]=pedidos.getMotivoFallido();
            busC.addRow(fila);
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}