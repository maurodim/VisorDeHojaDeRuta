/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.pantallas;

import facturacion.clientes.ClientesTango;
import impresoras.Impresora;
import interfacesPrograma.Facturar;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import objetos.Articulos;
import objetos.Comprobantes;
import tablas.MiModeloTablaBuscarCliente;
import tablas.MiModeloTablaFacturacion;
import visordehojaderuta.InicioVisorDeHojaDeRuta;
import visordehojaderuta.VisorDeHojaDeRuta;

/**
 *
 * @author hernan
 */
public class IngresoDePedidos extends javax.swing.JInternalFrame {

    /**
     * Creates new form IngresoDePedidos
     */
    private static ClientesTango cliT=new ClientesTango();
    private static ArrayList detalleDelPedido=new ArrayList();
    private static Articulos arti=new Articulos();
    private static ArrayList listadoDeBusqueda=new ArrayList();
    private static Double montoTotal=0.00;
    private static Comprobantes comp=new Comprobantes();
    
    public IngresoDePedidos(Object oob) {
        cliT=(ClientesTango)oob;
        comp.setCliente(cliT);
        initComponents();
        //this.jPanel2.requestFocus();
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        MiModeloTablaFacturacion facturas=new MiModeloTablaFacturacion();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FACTURACION - INGRESO DE ARTICULOS");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jTable1.setModel(facturas);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("TOTAL FACTURA :");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("jLabel2");

        jButton1.setText("IMPRIMIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("REMITIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("REPARTO");

        jLabel5.setText("FECHA");

        jCheckBox2.setText("PAGADO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 186, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jCheckBox2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(15, 15, 15))
        );

        jLabel3.setText("INGRESE SINONIMO :");

        jLabel4.setText("CANTIDAD :");

        jTextField1.requestFocus();
        jTextField1.setNextFocusableComponent(jList1);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 68, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            listadoDeBusqueda.clear();
            Facturar fart=new Articulos();
            listadoDeBusqueda=fart.listadoBusqueda(this.jTextField1.getText());
            cargarLista(listadoDeBusqueda);
            //this.jTextField2.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        int posicion=this.jList1.getSelectedIndex();
        arti=(Articulos)listadoDeBusqueda.get(posicion);
        System.err.println("ARTICULO SELECCIONADO :"+arti.getDescripcionArticulo());
        this.jTextField2.requestFocus();
        
    }//GEN-LAST:event_jList1MouseClicked

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            arti.setCantidad(Double.parseDouble(this.jTextField2.getText()));
            detalleDelPedido.add(arti);
            agregarRenglonTabla();
//                Double montoTotalX=(arti.getPrecioUnitario() * arti.getCantidad());
//                montoTotal=montoTotal + montoTotalX;
                 montrarMonto();
                 System.err.println("MONTO TOTAL "+montoTotal);
                this.jButton1.setVisible(true);
            this.jTextField1.setText("");
            this.jTextField2.setText("");
            this.jTextField1.requestFocus();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        detalleDelPedido.clear();
        listadoDeBusqueda.clear();
        montoTotal=0.00;
    }//GEN-LAST:event_formComponentHidden

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        detalleDelPedido.clear();
        listadoDeBusqueda.clear();
        montoTotal=0.00;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        verificar();
        Impresora imp=new Impresora();        
        String cadena=cliT.getCodigoCliente()+" - "+cliT.getRazonSocial()+"\n"+cliT.getDireccion();
        //comp.setCliente(cliT);
        //VisorDeHojaDeRuta
        comp.setVendedor(VisorDeHojaDeRuta.tG.getOperador());
        if(this.jCheckBox1.isSelected()){
            comp.setReparto(1);
            comp.setEntrega(String.valueOf(this.jTextField3.getText()));
        }
        if(this.jCheckBox2.isSelected()){
            comp.setPagado(1);
        }else{
            comp.setPagado(2);
        }
        comp.setArticulos(detalleDelPedido);
                DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        String fecha=dia+"/"+mes+"/"+ano;
        String fecha2=ano+"-"+mes+"-"+dia;
        comp.setFechaComprobante(fecha2);
        //comp.setFechaComprobante(fecha);
        int comprobanteTipo=0;
        if(cliT.getEmpresa().equals("sd")){
        if(cliT.getCondicionIva().equals("RI "))comprobanteTipo=1;
        if(cliT.getCondicionIva().equals("EX "))comprobanteTipo=3;
        if(cliT.getCondicionIva().equals("CF ")){
            if(montoTotal < 1000){
                comprobanteTipo=6;
            }else{
                comprobanteTipo=3;
            }
        }
        }else{
            comprobanteTipo=4;
        }
        comp.setTipoComprobante(comprobanteTipo);
        comp.setMontoTotal(montoTotal);
        try {
            imp.imprimir(comp);
    //        this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(IngresoDePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        verificar();
        Impresora imp=new Impresora();        
        String cadena=cliT.getCodigoCliente()+" - "+cliT.getRazonSocial()+"\n"+cliT.getDireccion();
        comp.setArticulos(detalleDelPedido);
        //comp.setFechaComprobante("17/01/2013");
        int comprobanteTipo=0;
        if(cliT.getEmpresa().equals("sd")){
            comprobanteTipo=2;
        }else{
            comprobanteTipo=5;
        }
        comp.setTipoComprobante(comprobanteTipo);
        try {
            imp.imprimir(comp);
        } catch (SQLException ex) {
            Logger.getLogger(IngresoDePedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        detalleDelPedido.clear();
        listadoDeBusqueda.clear();
        montoTotal=0.00;        
        SeleccionDeClientes sel=new SeleccionDeClientes();
        InicioVisorDeHojaDeRuta.jDesktopPane1.add(sel);
        sel.setVisible(true);
        sel.toFront();
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
private void cargarLista(ArrayList lista){
    DefaultListModel modelo=new DefaultListModel();
    Iterator il=lista.listIterator();
    Articulos art=new Articulos();
    while(il.hasNext()){
        art=(Articulos)il.next();
        System.out.println("DESCRIPCION "+art.getDescripcionArticulo());
        modelo.addElement(art.getCodigo()+" "+art.getDescripcionArticulo());
    }
    this.jList1.setModel(modelo);
}
private void agregarRenglonTabla(){
        MiModeloTablaFacturacion busC=new MiModeloTablaFacturacion();
        this.jTable1.removeAll();
        montoTotal=0.00;
        //ArrayList listadoPedidos=new ArrayList();
        this.jTable1.setModel(busC);
        Articulos pedidos=new Articulos();
        busC.addColumn("CODIGO");
        busC.addColumn("DESCRIPCION");
        busC.addColumn("CANTIDAD");
        busC.addColumn("MONTO");
        Object[] fila=new Object[4];
        Iterator irP=detalleDelPedido.listIterator();
        while(irP.hasNext()){
            pedidos=(Articulos) irP.next();
            fila[0]=pedidos.getCodigo();
            fila[1]=pedidos.getDescripcionArticulo();
            fila[2]=pedidos.getCantidad();
            Double precioUnitario=0.00;
            switch (cliT.getListaDePrecios()){
                case 2:
                    precioUnitario=pedidos.getPrecioUnitario();
                    break;
                case 15:
                    precioUnitario= pedidos.getPrecioUnitario() * 0.95;
                    break;
                case 12:
                    precioUnitario=pedidos.getPrecioUnitario() * 0.975;
                    break;
                case 25:
                    precioUnitario=pedidos.getPrecioUnitario()* 1.025;
                    break;
                case 50:
                    precioUnitario=pedidos.getPrecioUnitario()* 1.05;
                    break;
                case 100:
                    precioUnitario=pedidos.getPrecioUnitario()* 1.1;
                    break;
                case 150:
                    precioUnitario=pedidos.getPrecioUnitario()* 1.15;
                    break;
                case 200:
                    precioUnitario=pedidos.getPrecioUnitario()*1.2;
                    break;
                case 300:
                    precioUnitario=pedidos.getPrecioUnitario()*1.3;
                    break;
                case 500:
                    precioUnitario=pedidos.getPrecioUnitario() * 1.5;
                    break;
            }
            Double valor=(pedidos.getCantidad() * precioUnitario)*pedidos.getPesoUnitario();
            
            montoTotal=montoTotal + valor;
            fila[3]=valor;
            busC.addRow(fila);
        }

}
private void montrarMonto(){
    System.err.println("DESCUENTO :"+cliT.getDescuento());
    Double total=montoTotal * cliT.getDescuento();
    comp.setMontoTotal(total);
    this.jLabel2.setText(String.valueOf(total));
}
private void verificar(){
    int cantidad=this.jTable1.getRowCount();
    Articulos art=new Articulos();
    cantidad=cantidad - 1;
    for(int ah=0;ah < cantidad;ah++){
        
        art=(Articulos)detalleDelPedido.get(ah);
        //ah++;
        String descripcion=(String) this.jTable1.getValueAt(ah,1);
        art.setDescripcionArticulo(descripcion);
        String cant=String.valueOf(this.jTable1.getValueAt(ah, 2));
        Double cantida=Double.valueOf(cant).doubleValue();
        art.setCantidad(cantida);
        Double precioUni=(Double) this.jTable1.getValueAt(ah,3);
        Double tot=precioUni;
        art.setPrecioUnitario(tot);
        //montoTotal=montoTotal + tot;
        System.err.println("nimero "+ah+" decripcion "+descripcion+" limite "+cantidad);
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}