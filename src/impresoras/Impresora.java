package impresoras;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DecimalFormat;
import objetos.Articulos;
import objetos.Comprobantes;
import visordehojaderuta.VisorDeHojaDeRuta;

/**
 *
 * @author hernan
 */
public class Impresora {

    Font fuente = new Font("Dialog", Font.PLAIN, 7);
	PrintJob pj;	
	Graphics pagina;
	

	/********************************************************************
	*	A continuación el constructor de la clase. Aquí lo único que	*
	*	hago es tomar un objeto de impresion.							*
	********************************************************************/
	public Impresora()
	{
		pj = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "SCAT", null);
               
	}
			
	/********************************************************************
	*	A continuación el método "imprimir(String)", el encargado de 	*
	*	colocar en el objeto gráfico la cadena que se le pasa como 		*
	*	parámetro y se imprime.											*
	********************************************************************/
    public void imprimir(Comprobantes formulario) throws SQLException
	{
		//LO COLOCO EN UN try/catch PORQUE PUEDEN CANCELAR LA IMPRESION
            Comprobantes comp=formulario;
		//try
	//	{
			pagina = pj.getGraphics();
                        //pagina=pj.jobAttributes;
			pagina.setFont(fuente);
			pagina.setColor(Color.black);
                        DecimalFormat fr=new DecimalFormat("#####.##");
                        int a=0;
                        Articulos art;
                        //ENCABEZADO COLUMNAS DETALLE
                        Double bruto=0.00;
                        Double iv=0.00;
                        Double neto=0.00;
                        Integer numeroComp=comp.guardarNuevo(comp.getTipoComprobante());
                        comp.setNumero(numeroComp);
                        //String condicionDeVenta=null;
                        
                        switch (comp.getTipoComprobante()){
                            case 1:
                                //FACTURA A  
                                //for(int aa=0;aa < 3;aa++){
                                //encabezado
                                pagina.drawString(comp.getFechaComprobante(), 470, 87);
                                pagina.drawString(comp.getCliente().getCodigoCliente()+" "+comp.getCliente().getRazonSocial(), 45, 163);
                                pagina.drawString(comp.getCliente().getDireccion(), 45, 173);
                                pagina.drawString("- 3000", 183, 173);
                                pagina.drawString(comp.getCliente().getLocalidad(),233, 173);
                                pagina.drawString("COND DE VENTA :",370, 173);
                                pagina.drawString("IVA RESPONSABLE INSCRIPTO",45, 183);
                                pagina.drawString(comp.getCliente().getTelefono(),183, 183);
                                pagina.drawString("CUIT: "+comp.getCliente().getNumeroDeCuit(),370, 183);


                                pagina.drawString("Cod. Art", 45, 213);
                                pagina.drawString("Descripcion", 85, 213);
                                pagina.drawString("Und", 273, 213);
                                pagina.drawString("Cant", 293, 213);
                                pagina.drawString("Eq.", 363, 213);
                                pagina.drawString("Prec. Unit", 403,213);
                                pagina.drawString("Importe", 490, 213);
                                    //DETALLE DE ARTICULOS DE LA FACTURA
                                    a=213;
                                    for(int i=0;i< comp.getArticulos().size();i++){
                                        a=a+10;
                                        art=(Articulos)comp.getArticulos().get(i);
                                        pagina.drawString(art.getCodigo(), 45, a);
                                        pagina.drawString(art.getDescripcionArticulo(), 85, a);
                                        pagina.drawString(art.getUnidadDeMedida(), 270, a);
                                        pagina.drawString(fr.format(art.getCantidad()), 290,a);
                                        pagina.drawString(fr.format(art.getPesoUnitario()),360, a);
                                         Double tota=art.getPrecioUnitario() / art.getCantidad();
                                        pagina.drawString(fr.format(tota),400, a);
                                        pagina.drawString(fr.format(art.getPrecioUnitario()),490, a);

                                    }
                                    pagina.drawString("________________________________", 270,563);
                                    pagina.drawString("CONFORMIDAD DEL CLIENTE PARA PROCESAR ",260,577);
                                    //OBSERVACIONES
                                    pagina.drawString("LEYENDA 1",45,563);
                                    pagina.drawString("LEYENDA 2", 45, 573);
                                    pagina.drawString("LEYENDA 3",45,583);


                                    // SON PESOS:
                                    pagina.drawString("SON PESOS :",45,603);
                                    pagina.drawString("VENDEDOR :"+comp.getVendedor(), 45, 613);
                                    pagina.drawString("DTO : 0.00",125, 613);
                                    pagina.drawString("LP :"+comp.getCliente().getCondicionDePago(),195, 613);
                                    //TOTALES BRUTO - IVA - NETO
                                     bruto=comp.getMontoTotal() / 1.21;
                                    iv=comp.getMontoTotal() - bruto;
                                    neto=comp.getMontoTotal();
                                    pagina.drawString("21%",423,765);
                                    pagina.drawString(fr.format(bruto),45,775);
                                    pagina.drawString(fr.format(bruto),125,775);
                                    pagina.drawString(fr.format(bruto),225,775);
                                    pagina.drawString(fr.format(iv),423, 775);
                                    pagina.drawString(fr.format(neto),510,775);
                                   comp.guardarPedido(comp);
                                   
                                break;
                            case 2:
                                //REMITO SD
                                //encabezado
                                pagina.drawString(comp.getFechaComprobante(), 470, 87);
                                pagina.drawString(comp.getCliente().getCodigoCliente()+" "+comp.getCliente().getRazonSocial(), 45, 163);
                                pagina.drawString(comp.getCliente().getDireccion(), 45, 173);
                                pagina.drawString("- 3000", 183, 173);
                                pagina.drawString(comp.getCliente().getLocalidad(),233, 173);
                                pagina.drawString("COND DE VENTA :",370, 173);
                                pagina.drawString("IVA RESPONSABLE INSCRIPTO",45, 183);
                                pagina.drawString(comp.getCliente().getTelefono(),183, 183);
                                pagina.drawString("CUIT: 23-27620591-9",370, 183);


                                
                                pagina.drawString("Cod. Art", 45, 213);
                                pagina.drawString("Descripcion", 85, 213);
                                pagina.drawString("Und", 273, 213);
                                pagina.drawString("Cant", 293, 213);
                                pagina.drawString("Eq.", 363, 213);
                                //pagina.drawString("Prec. Unit", 403,213);
                                pagina.drawString("Total Kg", 490, 213);
                                //DETALLE DE ARTICULOS DE LA FACTURA
                                a=213;
                                
                                    for(int i=0;i< comp.getArticulos().size();i++){
                                        a=a+10;
                                        art=(Articulos)comp.getArticulos().get(i);
                                        pagina.drawString(art.getCodigo(), 45, a);
                                        pagina.drawString(art.getDescripcionArticulo(), 85, a);
                                        pagina.drawString(art.getUnidadDeMedida(), 270, a);
                                        pagina.drawString(fr.format(art.getCantidad()), 290,a);
                                        pagina.drawString(fr.format(art.getPesoUnitario()),360, a);
                                        Double total=art.getCantidad() * art.getPesoUnitario();
                                        //pagina.drawString(String.valueOf(art.getPrecioUnitario()),400, a);
                                        pagina.drawString(fr.format(total),490, a);

                                    }
                                //pagina.drawString("________________________________", 270,563);
                                //pagina.drawString("CONFORMIDAD DEL CLIENTE PARA PROCESAR ",260,577);
                                //OBSERVACIONES
                                pagina.drawString("",45,563);
                                pagina.drawString("", 45, 573);
                                pagina.drawString("",45,583);
                                comp.cantidadRemitidas(comp);

/*
                                // SON PESOS:
                                pagina.drawString("SON PESOS :",45,603);
                                pagina.drawString("VENDEDOR : 12", 45, 613);
                                pagina.drawString("DTO : 0.00",125, 613);
                                pagina.drawString("LP : 2",195, 613);
                                //TOTALES BRUTO - IVA - NETO
                                pagina.drawString("21%",423,765);
                                pagina.drawString("318.40",45,775);
                                pagina.drawString("318.40",125,775);
                                pagina.drawString("318.40",225,775);
                                pagina.drawString("66.86",423, 775);
                                pagina.drawString("385.26",510,775);
*/
                                break;
                            case 3:
                                //FACTURA B
                                //encabezado
                                pagina.drawString(comp.getFechaComprobante(), 470, 87);
                                pagina.drawString(comp.getCliente().getCodigoCliente()+" "+comp.getCliente().getRazonSocial(), 45, 163);
                                pagina.drawString(comp.getCliente().getDireccion(), 45, 173);
                                pagina.drawString("- 3000", 183, 173);
                                pagina.drawString(comp.getCliente().getLocalidad(),233, 173);
                                pagina.drawString("COND DE VENTA :",370, 173);
                                pagina.drawString("IVA RESPONSABLE INSCRIPTO",45, 183);
                                pagina.drawString(comp.getCliente().getTelefono(),183, 183);
                                pagina.drawString("CUIT: 23-27620591-9",370, 183);


                                

                                
                                pagina.drawString("Cod. Art", 45, 213);
                                pagina.drawString("Descripcion", 85, 213);
                                pagina.drawString("Und", 273, 213);
                                pagina.drawString("Cant", 293, 213);
                                pagina.drawString("Eq.", 363, 213);
                                pagina.drawString("Prec. Unit", 403,213);
                                pagina.drawString("Importe", 490, 213);
                                //DETALLE DE ARTICULOS DE LA FACTURA
                                a=213;
                                    for(int i=0;i< comp.getArticulos().size();i++){
                                        a=a+10;
                                        art=(Articulos)comp.getArticulos().get(i);
                                        pagina.drawString(art.getCodigo(), 45, a);
                                        pagina.drawString(art.getDescripcionArticulo(), 85, a);
                                        pagina.drawString(art.getUnidadDeMedida(), 270, a);
                                        pagina.drawString(fr.format(art.getCantidad()), 290,a);
                                        pagina.drawString("",360, a);
                                         Double tota=art.getPrecioUnitario() / art.getCantidad();
                                        pagina.drawString(fr.format(tota),400, a);
                                        pagina.drawString(fr.format(art.getPrecioUnitario()),490, a);

                                    }
                                 pagina.drawString("________________________________", 270,563);
                                pagina.drawString("CONFORMIDAD DEL CLIENTE PARA PROCESAR ",260,577);
                                //OBSERVACIONES
                                pagina.drawString("",45,563);
                                pagina.drawString("", 45, 573);
                                pagina.drawString("",45,583);


                                // SON PESOS:
                                     // SON PESOS:
                                    pagina.drawString("SON PESOS :",45,603);
                                    pagina.drawString("VENDEDOR :"+comp.getVendedor(), 45, 613);
                                    pagina.drawString("DTO : 0.00",125, 613);
                                    pagina.drawString("LP :"+comp.getCliente().getCondicionDePago(),195, 613);
                                //TOTALES BRUTO - IVA - NETO
                                    bruto=comp.getMontoTotal() / 1.21;
                                    iv=comp.getMontoTotal() - bruto;
                                    neto=comp.getMontoTotal();
                                    //pagina.drawString("21%",423,765);
                                    pagina.drawString(fr.format(bruto),45,775);
                                    pagina.drawString(fr.format(bruto),125,775);
                                    pagina.drawString(fr.format(bruto),225,775);
                                    //pagina.drawString(String.valueOf(iv),423, 775);
                                    pagina.drawString(fr.format(neto),510,775);
                                comp.guardarPedido(comp);

                                break;
                            case 4:
                                //BU                              
                                //encabezado
                                pagina.drawString(comp.getFechaComprobante(), 470, 57);
                                pagina.drawString(comp.getCliente().getCodigoCliente()+" "+comp.getCliente().getRazonSocial(), 45, 57);
                                pagina.drawString(comp.getCliente().getDireccion(), 45, 67);
                                pagina.drawString("- 3000", 183, 67);
                                pagina.drawString(comp.getCliente().getLocalidad(),233, 67);
                                pagina.drawString("COND DE VENTA :",370, 67);
                                pagina.drawString("BU",45, 77);
                                pagina.drawString(comp.getCliente().getTelefono(),183, 77);
                                pagina.drawString("",370, 77);

                                
                                
                                pagina.drawString("Cod. Art", 45, 87);
                                pagina.drawString("Descripcion", 85,87);
                                pagina.drawString("Und", 273, 87);
                                pagina.drawString("Cant", 293, 87);
                                pagina.drawString("Eq.", 363, 87);
                                pagina.drawString("Prec. Unit", 403,87);
                                pagina.drawString("Importe", 490, 87);
                                //DETALLE DE ARTICULOS DE LA FACTURA
                                a=97;
                                  for(int i=0;i< comp.getArticulos().size();i++){
                                        a=a+10;
                                        art=(Articulos)comp.getArticulos().get(i);
                                        pagina.drawString(art.getCodigo(), 45, a);
                                        pagina.drawString(art.getDescripcionArticulo(), 85, a);
                                        pagina.drawString(art.getUnidadDeMedida(), 270, a);
                                        pagina.drawString(fr.format(art.getCantidad()), 290,a);
                                        Double eq=art.getCantidad() * art.getPesoUnitario();
                                        pagina.drawString(fr.format(eq),360, a);
                                        Double tota=art.getPrecioUnitario() / art.getCantidad();
                                        pagina.drawString(fr.format(tota),400, a);
                                        pagina.drawString(fr.format(art.getPrecioUnitario()),490, a);

                                    }
                                pagina.drawString("________________________________", 270,337);
                                pagina.drawString("CONFORMIDAD DEL CLIENTE PARA PROCESAR ",260,347);
                                //OBSERVACIONES
                                pagina.drawString("",45,297);
                                pagina.drawString("", 45, 307);
                                pagina.drawString("",45,317);


                                // SON PESOS:
                                pagina.drawString("SON PESOS :",45,367);
                                pagina.drawString("VENDEDOR :"+comp.getVendedor(), 45, 377);
                                pagina.drawString("DTO : 5.00",195, 377);
                                pagina.drawString("LP : 2",245, 377);
                                //TOTALES BRUTO - IVA - NETO
                                neto=comp.getMontoTotal();
                                /*
                                
                                pagina.drawString("21%",423,765);
                                pagina.drawString("318.40",45,775);
                                pagina.drawString("318.40",125,775);
                                pagina.drawString("318.40",225,775);
                                pagina.drawString("66.86",423, 775);
                                */
                                pagina.drawString("Total :"+fr.format(neto), 490, 377);
                                comp.guardarPedido(comp);
                                break;
                            case 5:
                                //REMITOS BU
                                //encabezado
                                pagina.drawString(comp.getFechaComprobante(), 470, 57);
                                pagina.drawString(comp.getCliente().getCodigoCliente()+" "+comp.getCliente().getRazonSocial(), 45, 57);
                                pagina.drawString(comp.getCliente().getDireccion(), 45, 67);
                                pagina.drawString("- 3000", 183, 67);
                                pagina.drawString(comp.getCliente().getLocalidad(),233, 67);
                                pagina.drawString("COND DE VENTA :",370, 67);
                                pagina.drawString("BU",45, 77);
                                pagina.drawString(comp.getCliente().getTelefono(),183, 77);
                                pagina.drawString("",370, 77);


                                


                                
                                 pagina.drawString("Cod. Art", 45, 87);
                                pagina.drawString("Descripcion", 85, 87);
                                pagina.drawString("Und", 273, 87);
                                pagina.drawString("Cant", 293, 87);
                                pagina.drawString("Eq.", 363, 87);
                                //pagina.drawString("Prec. Unit", 403,213);
                                pagina.drawString("Total Kg", 490, 87);
                                //DETALLE DE ARTICULOS DE LA FACTURA
                                a=97;
                                for(int i=0;i< comp.getArticulos().size();i++){
                                        a=a+10;
                                        art=(Articulos)comp.getArticulos().get(i);
                                        pagina.drawString(art.getCodigo(), 45, a);
                                        pagina.drawString(art.getDescripcionArticulo(), 85, a);
                                        pagina.drawString(art.getUnidadDeMedida(), 270, a);
                                        pagina.drawString(fr.format(art.getCantidad()), 290,a);
                                        pagina.drawString(fr.format(art.getPesoUnitario()),360, a);
                                        Double total=art.getCantidad() * art.getPesoUnitario();
                                        //pagina.drawString(String.valueOf(art.getPrecioUnitario()),400, a);
                                        pagina.drawString(fr.format(total),490, a);

                                    }
                                comp.cantidadRemitidas(comp);
/*
                                
                                pagina.drawString("________________________________", 270,563);
                                pagina.drawString("CONFORMIDAD DEL CLIENTE PARA PROCESAR ",260,577);
                                //OBSERVACIONES
                                pagina.drawString("LEYENDA 1",45,563);
                                pagina.drawString("LEYENDA 2", 45, 573);
                                pagina.drawString("LEYENDA 3",45,583);


                                // SON PESOS:
                                pagina.drawString("SON PESOS :",45,603);
                                pagina.drawString("VENDEDOR : 12", 45, 613);
                                pagina.drawString("DTO : 0.00",125, 613);
                                pagina.drawString("LP : 2",195, 613);
                                //TOTALES BRUTO - IVA - NETO
                                pagina.drawString("21%",423,765);
                                pagina.drawString("318.40",45,775);
                                pagina.drawString("318.40",125,775);
                                pagina.drawString("318.40",225,775);
                                pagina.drawString("66.86",423, 775);
                                pagina.drawString("385.26",510,775);
*/
                                break;
                            case 6:
                                //TICKET                              
                                //encabezado
                                pagina.drawString("TICKET               "+comp.getFechaComprobante(), 370, 57);
                                pagina.drawString(comp.getCliente().getCodigoCliente()+" "+comp.getCliente().getRazonSocial(), 45, 57);
                                pagina.drawString(comp.getCliente().getDireccion(), 45, 67);
                                pagina.drawString("- 3000", 183, 67);
                                pagina.drawString(comp.getCliente().getLocalidad(),233, 67);
                                pagina.drawString("COND DE VENTA :",370, 67);
                                pagina.drawString("BU",45, 77);
                                pagina.drawString(comp.getCliente().getTelefono(),183, 77);
                                pagina.drawString("",370, 77);

                                
                                
                                pagina.drawString("Cod. Art", 45, 87);
                                pagina.drawString("Descripcion", 85,87);
                                pagina.drawString("Und", 273, 87);
                                pagina.drawString("Cant", 293, 87);
                                pagina.drawString("Eq.", 363, 87);
                                pagina.drawString("Prec. Unit", 403,87);
                                pagina.drawString("Importe", 490, 87);
                                //DETALLE DE ARTICULOS DE LA FACTURA
                                a=97;
                                  for(int i=0;i< comp.getArticulos().size();i++){
                                        a=a+10;
                                        art=(Articulos)comp.getArticulos().get(i);
                                        pagina.drawString(art.getCodigo(), 45, a);
                                        pagina.drawString(art.getDescripcionArticulo(), 85, a);
                                        pagina.drawString(art.getUnidadDeMedida(), 270, a);
                                        pagina.drawString(fr.format(art.getCantidad()), 290,a);
                                        Double eq=art.getCantidad() * art.getPesoUnitario();
                                        pagina.drawString(fr.format(eq),360, a);
                                        pagina.drawString(fr.format(art.getPrecioUnitario()),400, a);
                                        Double tota=art.getPrecioUnitario() * art.getCantidad();
                                        pagina.drawString(fr.format(tota),490, a);

                                    }
                                pagina.drawString("________________________________", 270,287);
                                pagina.drawString("CONFORMIDAD DEL CLIENTE PARA PROCESAR ",260,297);
                                //OBSERVACIONES
                                pagina.drawString("",45,297);
                                pagina.drawString("", 45, 307);
                                pagina.drawString("",45,317);


                                // SON PESOS:
                                pagina.drawString("SON PESOS :",45,367);
                                pagina.drawString("VENDEDOR : 01", 45, 377);
                                pagina.drawString("DTO : 5.00",125, 387);
                                pagina.drawString("LP : 2",195, 397);
                                //TOTALES BRUTO - IVA - NETO
/*
                                
                                pagina.drawString("21%",423,765);
                                pagina.drawString("318.40",45,775);
                                pagina.drawString("318.40",125,775);
                                pagina.drawString("318.40",225,775);
                                pagina.drawString("66.86",423, 775);
                                */
                                neto=comp.getMontoTotal();
                                pagina.drawString("Total :"+String.valueOf(neto),510,367);
                                comp.guardarPedido(comp);
                                break;
                                
                                
                        }
                        

			pagina.dispose();
			pj.end();
                        
		//}catch(Exception e)
		//{
		//	System.out.println("LA IMPRESION HA SIDO CANCELADA..."+e);
		//}
	}//FIN DEL PROCEDIMIENTO imprimir(String...)

					
}//FIN DE LA CLASE Impresora

 

