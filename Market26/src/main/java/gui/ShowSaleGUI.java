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
import domain.Sale;
import domain.User;


public class ShowSaleGUI extends JFrame {
	User usuario;
    File targetFile;
    BufferedImage targetImg;
    public JPanel panel_1;
    private static final int baseSize = 160;
	private static final String basePath="src/main/resources/images/";
	
	private static final long serialVersionUID = 1L;

	private JTextField fieldTitle=new JTextField();
	private JTextField fieldDescription=new JTextField();
	
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
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private JLabel statusField=new JLabel();
	private JFrame thisFrame;
	private final JButton btnComprar; 
	private JTextField textFieldComprar;
	private JLabel lblCompr;
	private JLabel lblRes;
	
	public ShowSaleGUI(Sale sale,User us) {
		usuario=us;
		thisFrame=this; 
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		//this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateProductGUI.CreateProduct"));

		fieldTitle.setText(sale.getTitle());
		fieldDescription.setText(sale.getDescription());

		fieldPrice.setText(Float.toString(sale.getPrice()));		
		
		labelStatus.setText(new SimpleDateFormat("dd-MM-yyyy").format(sale.getPublicationDate()));
		
		jLabelTitle.setBounds(new Rectangle(6, 56, 92, 20));
		
		jLabelPrice.setBounds(new Rectangle(6, 166, 101, 20));
		fieldPrice.setEditable(false);
		fieldPrice.setBounds(new Rectangle(137, 166, 60, 20));

		
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonClose.setBounds(new Rectangle(16, 268, 114, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);			}
		});

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
		
		labelStatus.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		labelStatus.setBounds(37, 231, 289, 16);
		getContentPane().add(labelStatus);
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		String file=sale.getFile();
		if (file!=null) {
			Image img=facade.downloadImage(file);
			targetImg = rescale((BufferedImage)img);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(new JLabel(new ImageIcon(targetImg))); 
		}
		System.out.println("status: "+sale.getStatus());
		statusField = new JLabel(Utils.getStatus(sale.getStatus())); 
		statusField.setBounds(137, 191, 92, 16);
		getContentPane().add(statusField);
		
		btnComprar= new JButton(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.bontoncomprar"));
		btnComprar.setBounds(180, 282, 101, 30);
		getContentPane().add(btnComprar);
		
		lblCompr = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.lblcomprar"));
		lblCompr.setBounds(180, 231, 114, 12);
		getContentPane().add(lblCompr);
		lblCompr.setVisible(false);
		textFieldComprar = new JTextField(); 
		textFieldComprar.setBounds(185, 254, 96, 18);
		getContentPane().add(textFieldComprar);
		textFieldComprar.setColumns(10);
		textFieldComprar.setVisible(false);
		lblRes = new JLabel();
		lblRes.setSize(300, 18);
		lblRes.setLocation(15, 305);
		lblCompr.setBounds(180, 231, 114, 12);
		getContentPane().add(lblRes);
		lblRes.setVisible(false);
		
		
		btnComprar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String ofertTexto=textFieldComprar.getText();
				try {
					lblRes.setVisible(true);
					boolean res=facade.anadirCompra(ofertTexto,sale,(Buyer) usuario);
					if(res) {
						lblCompr.setVisible(false);
						textFieldComprar.setVisible(false);
						btnComprar.setVisible(false);
						lblRes.setText(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.lblResOK"));
					}else {
						lblRes.setText(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.lblResFAIL"));
					}
				}catch(NumberFormatException a) {
					lblRes.setText(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.lblResFAIL"));
				}catch (Exception ex) {
					lblRes.setText(ex.getMessage());
					lblRes.setForeground(java.awt.Color.RED);
				}	
			}
		});
		
		btnComprar.setVisible(false);
		
		setVisible(true);
		
		if(usuario instanceof Buyer) {
			btnComprar.setVisible(true);
			lblCompr.setVisible(true);
			textFieldComprar.setVisible(true);
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

