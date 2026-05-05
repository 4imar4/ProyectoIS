package gui;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;

import businessLogic.BLFacade;
import domain.Buyer;
import domain.Offer;
import domain.Sale;
import domain.Seller;
import domain.User;


public class ShowOfferGUI extends JFrame {
	User us;
    File targetFile;
    BufferedImage targetImg;
    public JPanel panel_1;
    private static final int baseSize = 160;
	private static final String basePath="src/main/resources/images/";
	
	private static final long serialVersionUID = 1L;

	private JTextField fieldTitle=new JTextField();
	private JTextField fieldDescription=new JTextField();
	private JTextField textoDevolucion;

	
	JLabel labelStatus = new JLabel(); 

	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.Title"));
	private JLabel jLabelDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Description")); 
	private JLabel jLabelProductStatus = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Status"));
	private JLabel jLabelPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"));
	private JTextField fieldPrice = new JTextField();
	private File selectedFile;
    private String irudia;

	private JScrollPane scrollPaneEvents = new JScrollPane();
	DefaultComboBoxModel<String> statusOptions = new DefaultComboBoxModel<String>();
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JButton jButtonAceptar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Aceptar"));
	private JButton jButtonEliminarOfertaPendiente = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.EliminarOferta")); //Parte 3, eliminar oferta pendiente
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private JLabel statusField=new JLabel();
	private JFrame thisFrame;
	private JLabel lblCompr;
	private JTextField textFieldCompr;
	private JLabel lblPriceCom;
	private JLabel lblMotivo;
	private JTextField textFieldPriceComp;
	private JTextField textFieldValoracion;
	private JLabel lblValoracion;
	private JButton btnValoracion;
	
	public ShowOfferGUI(Offer offer,User user) {
		this.us=user;
		thisFrame=this; 
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		//this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateProductGUI.CreateProduct"));

		fieldTitle.setText(offer.getSale().getTitle());
		fieldDescription.setText(offer.getSale().getDescription());

		fieldPrice.setText(Float.toString(offer.getSale().getPrice()));		
		
		labelStatus.setText(new SimpleDateFormat("dd-MM-yyyy").format(offer.getSale().getPublicationDate()));
		
		jLabelTitle.setBounds(new Rectangle(6, 56, 92, 20));
		
		jLabelPrice.setBounds(new Rectangle(6, 166, 101, 20));
		fieldPrice.setEditable(false);
		fieldPrice.setBounds(new Rectangle(137, 166, 60, 20));

		
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonClose.setBounds(new Rectangle(23, 257, 92, 35));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);			}
		});

		//Parte 3, eliminar oferta pendiente
		jButtonEliminarOfertaPendiente.setBounds(new Rectangle(6, 296, 130, 26));
		jButtonEliminarOfertaPendiente.setVisible(false);
		getContentPane().add(jButtonEliminarOfertaPendiente);
				
		jLabelMsg.setBounds(new Rectangle(275, 214, 305, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(6, 231, 320, 20));
		jLabelError.setForeground(Color.red);
		

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jLabelTitle, null);
		
		
		this.getContentPane().add(jLabelPrice, null);
		this.getContentPane().add(fieldPrice, null);
		
		jLabelProductStatus.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelProductStatus.setBounds(6, 187, 140, 25);
		getContentPane().add(jLabelProductStatus);
		
		jLabelDescription.setBounds(6, 81, 109, 16);
		getContentPane().add(jLabelDescription);
		fieldTitle.setEditable(false);
		
		
		fieldTitle.setBounds(128, 53, 370, 26);
		getContentPane().add(fieldTitle);
		fieldTitle.setColumns(10);
		fieldDescription.setEditable(false);
		
		
		fieldDescription.setBounds(127, 81, 371, 73);
		getContentPane().add(fieldDescription);
		fieldDescription.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBounds(318, 166, 180, 160);
		getContentPane().add(panel_1);
		panel_1.setVisible(true);
		
		labelStatus.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		labelStatus.setBounds(37, 231, 289, 16);
		getContentPane().add(labelStatus);
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		String file=offer.getSale().getFile();
		if (file!=null) {
			Image img=facade.downloadImage(file);
			targetImg = rescale((BufferedImage)img);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(new JLabel(new ImageIcon(targetImg))); 
		}
		System.out.println("status: "+offer.getSale().getStatus());
		statusField = new JLabel(Utils.getStatus(offer.getSale().getStatus())); 
		statusField.setBounds(137, 191, 92, 16);
		getContentPane().add(statusField);
		
		lblCompr = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.lblComprador")); 
		lblCompr.setBounds(142, 222, 123, 20);
		getContentPane().add(lblCompr);
		
		textFieldCompr = new JTextField();
		textFieldCompr.setEditable(false);
		textFieldCompr.setText(offer.getBuyer().getEmail());
		textFieldCompr.setBounds(142, 247, 140, 18);
		getContentPane().add(textFieldCompr);
		textFieldCompr.setColumns(10);
		
		lblPriceCom = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.lblPrecio"));
		lblPriceCom.setBounds(142, 273, 101, 21);
		getContentPane().add(lblPriceCom);
		
		textFieldPriceComp = new JTextField();
		textFieldPriceComp.setEditable(false);
		textFieldPriceComp.setText(Float.toString(offer.getOfferedPrice()));
		textFieldPriceComp.setBounds(142, 300, 60, 18);
		getContentPane().add(textFieldPriceComp);
		textFieldPriceComp.setColumns(10);
		
		setVisible(true);
		
		if(us instanceof Seller) {
			jButtonAceptar.setBounds(new Rectangle(6, 296, 130, 26));
			jButtonAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BLFacade facade = MainGUI.getBusinessLogic();
						facade.aceptarOferta(offer);
						thisFrame.setVisible(false);
				}
			});
			getContentPane().add(jButtonAceptar);
		}
		
		
		if(us instanceof Buyer && offer.getAccepted()==0) {
		    jButtonEliminarOfertaPendiente.setVisible(true);

		    jButtonEliminarOfertaPendiente.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            BLFacade facade = MainGUI.getBusinessLogic();
		            facade.eliminarOfertaPendiente(offer);
		            thisFrame.setVisible(false);
		        }
		    });
		}
		textoDevolucion = new JTextField();
		textoDevolucion.setBounds(320, 200, 157, 67);
		getContentPane().add(textoDevolucion);
		textoDevolucion.setColumns(10);
		textoDevolucion.setVisible(false);
		textoDevolucion.setEditable(false);
		
		lblMotivo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.lblMotivo"));
		lblMotivo.setBounds(310, 170, 101, 21);
		getContentPane().add(lblMotivo);
		lblMotivo.setVisible(false);
		
		if(offer.getAccepted()==-2) {
			panel_1.setVisible(false);
			textoDevolucion.setVisible(true);
			textoDevolucion.setText(offer.getMotivoDevolucion());
			lblMotivo.setVisible(true);
		}
		
		lblValoracion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.valoracion"));
		lblValoracion.setBounds(256, 16, 153, 16);
		getContentPane().add(lblValoracion);
		lblValoracion.setVisible(false);
		
		textFieldValoracion = new JTextField();
		textFieldValoracion.setEditable(true);
		textFieldValoracion.setBounds(402, 15, 75, 18);
		getContentPane().add(textFieldValoracion);
		textFieldValoracion.setColumns(10);
		textFieldValoracion.setVisible(false);
		
		btnValoracion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowOfferGUI.btnValoracion"));
		btnValoracion.setBounds(487, 15, 93, 18);
		getContentPane().add(btnValoracion);
		btnValoracion.setVisible(false);
		
		if(us instanceof Buyer && (offer.getAccepted()==1 || offer.getAccepted()==-2)) {
			textFieldValoracion.setVisible(true);
			lblValoracion.setVisible(true);
			btnValoracion.setVisible(true);

		    btnValoracion.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	textFieldValoracion.setEditable(false);
					btnValoracion.setVisible(false);
		            String v=textFieldValoracion.getText();
		            try {
		            	double dv=Double.parseDouble(v);
		            	if (dv>10.0 || dv<0.0) {
		            		throw new Exception();
		            	}
		            	facade.setValoracion(dv,offer.getSale().getSeller().getEmail());
		            	textFieldValoracion.setText("Ok");
		            }catch(NumberFormatException ex) {
		            	textFieldValoracion.setText("ERROR.");
		            }catch(Exception exc) {
		            	textFieldValoracion.setText("ERROR.");
		            }
		        }
		    });
		}
		
	}	 
	public BufferedImage rescale(BufferedImage originalImage)
    {
        BufferedImage resizedImage = new BufferedImage(baseSize, baseSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, baseSize, baseSize, null);
        g.dispose();
        return resizedImage;
    }
}

