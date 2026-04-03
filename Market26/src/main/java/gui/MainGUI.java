package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import businessLogic.BLFacade;
import domain.Seller;
import domain.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {
	String corr="";
    private User usuario;
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton jButtonVerCarrito = null;

	private JButton jButtonAcceptQueries=null;
	private JButton jButtonMiCartera;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade facade){
		appFacadeInterface=facade;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton jButtonSignIn;
	private JButton jButtonLogin;
	private JLabel lblIntroCuenta;
	private JLabel lblIntroNombre;

	private JLabel lblIntroCon;
	private JTextField textField;
	private JTextField textFieldNom;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_2;
	private final ButtonGroup buttonGroup2 = new ButtonGroup();
	private JRadioButton rdbtnBuy;
	private JRadioButton rdbtnSell;
	private JButton btnEntrar;
	private JButton btnReg;
	private JTextArea textArea;
	private JButton btnBack;

	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();

		
		
		this.setSize(495, 290);
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jLabelSelectOption.setBounds(0, 0, 480, 63);
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		
		jButtonQueryQueries = new JButton();
		jButtonQueryQueries.setBounds(0, 126, 480, 63);
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QuerySales"));
		jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new QuerySalesGUI(usuario);
			
				a.setVisible(true);
			}
		});
		
		jButtonQueryQueries.setEnabled(false);
		jButtonQueryQueries.setVisible(false);
		
		jButtonVerCarrito = new JButton();
		jButtonVerCarrito.setBounds(0, 189, 480, 63);
		jButtonVerCarrito.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.VerCarrito"));
		jButtonVerCarrito.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new CarritoGUI(usuario);
			
				a.setVisible(true);
			}
		});
		jButtonVerCarrito.setEnabled(false);
		jButtonVerCarrito.setVisible(false);
		

		jButtonAcceptQueries = new JButton();
		jButtonAcceptQueries.setBounds(240, 126, 240, 63);
		jButtonAcceptQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.AcceptSales"));
		jButtonAcceptQueries.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new AcceptSalesGUI(usuario);
			
				a.setVisible(true);
			}
		});
		jButtonAcceptQueries.setVisible(false);
		jButtonQueryQueries.setEnabled(false);
		jButtonQueryQueries.setVisible(false);
		Seller a;
		
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jLabelSelectOption);
		jContentPane.add(jButtonVerCarrito);
		
		jButtonSignIn = new JButton(); 
		jButtonSignIn.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SignIn"));
		jButtonSignIn.setBounds(0, 126, 480, 63);
		jButtonSignIn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jButtonLogin.setVisible(false);
				jButtonLogin.setEnabled(false);
				jButtonSignIn.setVisible(false);
				jButtonSignIn.setEnabled(false);
				jLabelSelectOption.setVisible(false);
				lblIntroCuenta.setVisible(true);
				lblIntroCon.setVisible(true);
				textField.setVisible(true);
				passwordField.setVisible(true);
				textArea.setVisible(true);
				rdbtnBuy.setVisible(false);
				rdbtnSell.setVisible(false);
				lblNewLabel_2.setVisible(false);
				btnReg.setEnabled(true);
				btnReg.setVisible(true);
				rdbtnBuy.setVisible(true);
				rdbtnSell.setVisible(true);
				lblNewLabel_2.setVisible(true);
				lblIntroNombre.setVisible(true);
				textFieldNom.setVisible(true);
				btnReg.setEnabled(false);
			}
		});
		jContentPane.add(jButtonSignIn);
		jContentPane.add(jButtonAcceptQueries);
		jButtonCreateQuery = new JButton();
		jButtonCreateQuery.setBounds(0, 63, 480, 63);
		jContentPane.add(jButtonCreateQuery);
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateSale"));
		jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new CreateSaleGUI(usuario.getEmail());
				a.setVisible(true);
			}
		});
		jButtonCreateQuery.setEnabled(false);
		jButtonCreateQuery.setVisible(false);

		
		jButtonLogin = new JButton();
		jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jButtonLogin.setVisible(false);
				jButtonLogin.setEnabled(false);
				jButtonSignIn.setVisible(false);
				jButtonSignIn.setEnabled(false);
				jLabelSelectOption.setVisible(false);
				lblIntroCuenta.setVisible(true);
				lblIntroCon.setVisible(true);
				textField.setVisible(true);
				passwordField.setVisible(true);
				textArea.setVisible(true);
				rdbtnBuy.setVisible(false);
				rdbtnSell.setVisible(false);
				lblNewLabel_2.setVisible(false);
				btnEntrar.setEnabled(true);
				btnEntrar.setVisible(true);
				
			}
		});
		jButtonLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.LogIn"));
		jButtonLogin.setBounds(0, 63, 480, 63);
		jContentPane.add(jButtonLogin);
		
		
		jContentPane.add(jButtonQueryQueries);
		
		
		
		
		setContentPane(jContentPane);
		
		rdbtnNewRadioButton = new JRadioButton("English");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("eus"));
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				paintAgain();
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_2);
		
			panel = new JPanel();
			panel.setBounds(126, 190, 240, 26);
			panel.add(rdbtnNewRadioButton_1);
			panel.add(rdbtnNewRadioButton_2);
			panel.add(rdbtnNewRadioButton);
			jContentPane.add(panel);
			
		lblIntroCuenta = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.IntrCu")); 
		lblIntroCuenta.setBounds(40, 35, 153, 33);
		jContentPane.add(lblIntroCuenta);
		lblIntroCuenta.setVisible(false);
		
		lblIntroNombre = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Nom")); 
		lblIntroNombre.setBounds(40, 0, 153, 33);
		jContentPane.add(lblIntroNombre);
		lblIntroNombre.setVisible(false);
		
		lblIntroCon = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.IntrCon")); 
		lblIntroCon.setBounds(40, 83, 140, 37);
		jContentPane.add(lblIntroCon);
		lblIntroCon.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(223, 41, 153, 26);
		jContentPane.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(223, 6, 153, 26);
		jContentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		textFieldNom.setVisible(false);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(223, 84, 153, 26);
		jContentPane.add(passwordField);
		passwordField.setVisible(false);

		lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Eleccion"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(40, 125, 153, 20);
		jContentPane.add(lblNewLabel_2);
		
		rdbtnSell = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Tipo1"));
		buttonGroup2.add(rdbtnSell);
		rdbtnSell.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnSell.setBounds(190, 125, 102, 20);
		jContentPane.add(rdbtnSell);
		
		rdbtnBuy = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Tipo2"));
		buttonGroup2.add(rdbtnBuy);
		rdbtnBuy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnBuy.setBounds(307, 125, 102, 20);
		jContentPane.add(rdbtnBuy);
		rdbtnBuy.setVisible(false);
		rdbtnSell.setVisible(false);
		lblNewLabel_2.setVisible(false);
		rdbtnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnReg.setEnabled(true);
			}
		});
		rdbtnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnReg.setEnabled(true);
			}
		});
		
		btnEntrar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Entrar"));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String correo=textField.getText(); 
				String contrasena= new String(passwordField.getPassword()); 
				
				int log=appFacadeInterface.hacerLogin(correo,contrasena);
						     
				if (log==-1) {
					textArea.setText("No se ha encontrado el usuario.\nVaya atras y registrese si no existe");
					btnBack.setVisible(true);
					btnBack.setEnabled(true);
				}else if(log==0) {
					textArea.setText("Contraseña incorrecta. Vuelva a intentarlo");
					btnBack.setVisible(false);
				}else{
					usuario=appFacadeInterface.getUsuario(correo);
					corr=usuario.getEmail();
					lblIntroCuenta.setVisible(false);
					lblIntroCon.setVisible(false);
					textField.setVisible(false);
					passwordField.setVisible(false);
					textArea.setVisible(false);
					jLabelSelectOption.setVisible(true);
					btnEntrar.setVisible(false);
					btnBack.setVisible(false);
//kailai------------------------------------------------------------------------------------------------------------------------
					jButtonMiCartera.setVisible(true);
//fin kailai--------------------------------------------------------------------------------------------------------------------
					if(usuario instanceof Seller) {
						jButtonCreateQuery.setVisible(true);
						jButtonQueryQueries.setVisible(true);
						jButtonCreateQuery.setEnabled(true);
						jButtonQueryQueries.setEnabled(true);
						jButtonQueryQueries.setBounds(0, 126, 240, 63);
						jButtonAcceptQueries.setVisible(true);
						
					}else {
						jButtonQueryQueries.setVisible(true);
						jButtonQueryQueries.setEnabled(true);
						jButtonQueryQueries.setBounds(0, 55, 480, 63);
						jButtonVerCarrito.setVisible(true);
						jButtonVerCarrito.setEnabled(true);
						jButtonVerCarrito.setBounds(0, 118, 480, 63);
						
					}
					paintAgain();
				}		  
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEntrar.setBounds(144, 154, 153, 29);
		jContentPane.add(btnEntrar);
		btnEntrar.setEnabled(false);
		btnEntrar.setVisible(false);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(114, 217, 295, 34);
		jContentPane.add(textArea);
		textArea.setVisible(false);
		
		btnBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Back"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					     
				dispose();
				MainGUI a=new MainGUI();
				a.setVisible(true);
				paintAgain();
				
			}			  
			
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(311, 142, 98, 25);
		jContentPane.add(btnBack);
		btnBack.setVisible(false);
		
		btnReg = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Reg"));
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String correo=textField.getText(); 
				String contrasena= new String(passwordField.getPassword());
				String tipo=null;
				String nombre=textFieldNom.getText();
				if (rdbtnBuy.isSelected()) 
					tipo=rdbtnBuy.getText(); 
				else  
					if (rdbtnSell.isSelected()) 
						tipo=rdbtnSell.getText();
				
				if(!correo.equals("")) {
					boolean reg=appFacadeInterface.registrarse(correo,contrasena,tipo,nombre);
							     
					if (!reg) {
						textArea.setText("Existe este usuario.\nVaya atras e inicie sesion");
						btnBack.setVisible(true);
						btnBack.setEnabled(true);
					}else{
						usuario=appFacadeInterface.getUsuario(correo);
						corr=usuario.getEmail();
						lblIntroCuenta.setVisible(false);
						lblIntroCon.setVisible(false);
						textField.setVisible(false);
						passwordField.setVisible(false);
						textArea.setVisible(false);
						jLabelSelectOption.setVisible(true);
						btnReg.setVisible(false);
						rdbtnBuy.setVisible(false);
						rdbtnSell.setVisible(false);
						btnBack.setVisible(false);
						lblIntroNombre.setVisible(false);
						textFieldNom.setVisible(false);
//kailai------------------------------------------------------------------------------------------------------------------------
						jButtonMiCartera.setVisible(true);
//fin kailai--------------------------------------------------------------------------------------------------------------------
						if(usuario instanceof Seller) {
							jButtonCreateQuery.setVisible(true);
							jButtonQueryQueries.setVisible(true);
							jButtonCreateQuery.setEnabled(true);
							jButtonQueryQueries.setEnabled(true);
							jButtonQueryQueries.setBounds(0, 126, 240, 63);
							jButtonAcceptQueries.setVisible(true);
							
						}else {
							jButtonQueryQueries.setVisible(true);
							jButtonQueryQueries.setEnabled(true);
							jButtonQueryQueries.setBounds(0, 55, 480, 63);
							jButtonVerCarrito.setVisible(true);
							jButtonVerCarrito.setEnabled(true);
							jButtonVerCarrito.setBounds(0, 118, 480, 63);
  
						}
						
						paintAgain();
					}
				}else {
					textArea.setText("Introduzca un correo");
				}
						  
			}
		});
		btnReg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReg.setBounds(144, 154, 153, 29);
		jContentPane.add(btnReg);
		btnReg.setEnabled(false);
		btnReg.setVisible(false);
		
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle"));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
//kailai---------------------------------------------------------------------------------------------------------------------------------
		jButtonMiCartera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MiCartera"));
		jButtonMiCartera.setBounds(360, 15, 110, 30); 
		jButtonMiCartera.setVisible(false);
		jButtonMiCartera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuario != null) {
					JFrame carteraWindow = new WalletGUI(usuario.getEmail());
					carteraWindow.setVisible(true);
					carteraWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
				}
			}
		});
		jContentPane.add(jButtonMiCartera);
//fin kailai-------------------------------------------------------------------------------------------------------------------------------
	}
	
	private void paintAgain() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QuerySales"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateSale"));
		jButtonLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.LogIn"));
		jButtonSignIn.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SignIn"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle")+corr);
		lblIntroCon.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.IntrCon"));
		lblIntroCuenta.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.IntrCu"));
		lblNewLabel_2.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Eleccion"));
		rdbtnSell.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Tipo1"));
		rdbtnBuy.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Tipo2"));
		btnEntrar.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Entrar"));
		btnBack.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Back"));
		btnReg.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Reg"));
		lblIntroNombre.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Nom"));
		jButtonAcceptQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.AcceptSales"));
		jButtonMiCartera.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MiCartera"));
		jButtonVerCarrito.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.VerCarrito"));

	}
	
	
} // @jve:decl-index=0:visual-constraint="0,0"

