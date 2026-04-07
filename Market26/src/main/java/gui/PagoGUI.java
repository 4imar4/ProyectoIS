package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class PagoGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private boolean pagoAprobado = false; 
	private JTextField txtTarjeta;
	private JTextField txtTitular;
	private JTextField txtCaducidad;
	private JTextField txtCVV;

	public PagoGUI(Window parent, float cantidad) {
		super(parent, ResourceBundle.getBundle("Etiquetas").getString("PagoGUI.Title"), Dialog.ModalityType.APPLICATION_MODAL); 
		setSize(380, 260);
		getContentPane().setLayout(null);
		
		JLabel lblCantidad = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PagoGUI.Total") + cantidad + " €");
		lblCantidad.setBounds(20, 15, 300, 25);
		getContentPane().add(lblCantidad);

		JLabel lblTarjeta = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PagoGUI.Tarjeta"));
		lblTarjeta.setBounds(20, 50, 120, 20);
		getContentPane().add(lblTarjeta);
		
		txtTarjeta = new JTextField();
		txtTarjeta.setBounds(150, 50, 180, 20);
		getContentPane().add(txtTarjeta);

		JLabel lblTitular = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PagoGUI.Titular"));
		lblTitular.setBounds(20, 80, 120, 20);
		getContentPane().add(lblTitular);
		
		txtTitular = new JTextField();
		txtTitular.setBounds(150, 80, 180, 20);
		getContentPane().add(txtTitular);

		JLabel lblCaducidad = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PagoGUI.Caducidad"));
		lblCaducidad.setBounds(20, 110, 80, 20);
		getContentPane().add(lblCaducidad);
		
		txtCaducidad = new JTextField();
		txtCaducidad.setBounds(100, 110, 60, 20);
		getContentPane().add(txtCaducidad);
		
		JLabel lblCVV = new JLabel("CVV:");
		lblCVV.setBounds(180, 110, 40, 20);
		getContentPane().add(lblCVV);
		
		txtCVV = new JTextField();
		txtCVV.setBounds(220, 110, 50, 20);
		getContentPane().add(txtCVV);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(200, 15, 160, 25); 
		getContentPane().add(lblError);

		JButton btnPagar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("PagoGUI.BtnPagar") + cantidad + " €");
		btnPagar.setBounds(40, 170, 130, 30);
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTarjeta.getText().isEmpty() || txtCVV.getText().isEmpty()) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("PagoGUI.ErrorDatos"));
				} else {
					pagoAprobado = true; 
					dispose(); 
				}
			}
		});
		getContentPane().add(btnPagar);

		JButton btnCancelar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("PagoGUI.BtnCancelar"));
		btnCancelar.setBounds(190, 170, 100, 30);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pagoAprobado = false; 
				dispose(); 
			}
		});
		getContentPane().add(btnCancelar);
	}

	public boolean isPagoAprobado() {
		return pagoAprobado;
	}
}