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
	private JLabel lblCompr;
	private JTextField textFieldCompr;
	private JLabel lblPriceCom;
	private JTextField textFieldPriceComp;
	
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

