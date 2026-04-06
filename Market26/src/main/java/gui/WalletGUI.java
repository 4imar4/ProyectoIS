package gui;

import businessLogic.BLFacade;
import domain.Transaccion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;
import java.util.ResourceBundle;


public class WalletGUI extends JFrame {

    private String userEmail;
    private JLabel lblSaldo;
    private JTextField txtCantidad;
    private JTable tableHistorial;
    private DefaultTableModel tableModel;
    private BLFacade facade;

    public WalletGUI(String email) {
        this.userEmail = email;
        this.facade = MainGUI.getBusinessLogic();

        setTitle(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Title") + email);
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(null);

        lblSaldo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.SaldoActual") + 0.0);
        lblSaldo.setBounds(30, 20, 200, 20);
        getContentPane().add(lblSaldo);

        JLabel lblRecargar = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.CantidadRecargar"));
        lblRecargar.setBounds(30, 60, 140, 20);
        getContentPane().add(lblRecargar);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(165, 60, 100, 20);
        getContentPane().add(txtCantidad);

        JButton btnRecargar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.BtnRecargar"));
        btnRecargar.setBounds(275, 60, 100, 20);
        getContentPane().add(btnRecargar);

        String[] columnNames = {
        	    ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.ColFecha"),
        	    ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.ColTipo"),
        	    ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.ColCantidad"),
        	    ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.ColDesc")
        };
        
        tableModel = new DefaultTableModel(null, columnNames);
        tableHistorial = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(tableHistorial);
        scrollPane.setBounds(30, 110, 420, 200);
        getContentPane().add(scrollPane);
        
        JLabel lblMensaje = new JLabel("");
        lblMensaje.setForeground(new Color(0, 0, 0));
        lblMensaje.setBounds(203, 26, 236, 14);
        getContentPane().add(lblMensaje);

        actualizarDatos();
		tableHistorial.setEnabled(false);


        btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float cantidad = Float.parseFloat(txtCantidad.getText());
					if (cantidad > 0) {
						facade.recargarSaldo(userEmail, cantidad);
						txtCantidad.setText("");
						actualizarDatos(); 
						lblMensaje.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.MsgExito") + cantidad + " €");
					} else {
						lblMensaje.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.MsgErrorCero"));
					}
				} catch (NumberFormatException ex) {
					lblMensaje.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.MsgErrorNum"));
				}
			}
		});
    }

    private void actualizarDatos() {
        float saldoActual = facade.getSaldo(userEmail);
        lblSaldo.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.SaldoActual") + saldoActual + " €");

        tableModel.setRowCount(0); 
        List<Transaccion> lista = facade.getTransacciones(userEmail);
        
        if (lista != null) {
            for (Transaccion t : lista) {
                Object[] row = {
                    t.getFecha().toString(),
                    t.getTipo(),
                    t.getCantidad() + " €",
                    t.getDescripcion()
                };
                tableModel.addRow(row); 
            }
        }
    }
}