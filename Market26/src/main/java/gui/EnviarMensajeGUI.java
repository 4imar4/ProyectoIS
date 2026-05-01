package gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ResourceBundle;

public class EnviarMensajeGUI extends JFrame {
    private String miEmail;
    private JTextField txtDestino;
    private JTextField txtAsunto;
    private JTextArea txtCuerpo;
    private JLabel lblError;

    public EnviarMensajeGUI(String email) {
        this.miEmail = email;
        inicializarComponentes();
    }

    public EnviarMensajeGUI(String email, String destino, String asunto) {
        this.miEmail = email;
        inicializarComponentes();
        
        txtDestino.setText(destino);
        txtDestino.setEditable(false);
        txtAsunto.setText(asunto);
    }

    private void inicializarComponentes() {
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("EnviarMensaje.Titulo"));
        setBounds(200, 200, 500, 400);
        getContentPane().setLayout(null);

        JLabel lblPara = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EnviarMensaje.Para"));
        lblPara.setBounds(20, 20, 100, 20);
        getContentPane().add(lblPara);

        txtDestino = new JTextField();
        txtDestino.setBounds(120, 20, 340, 25);
        getContentPane().add(txtDestino);

        JLabel lblTema = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EnviarMensaje.Asunto"));
        lblTema.setBounds(20, 60, 100, 20);
        getContentPane().add(lblTema);

        txtAsunto = new JTextField();
        txtAsunto.setBounds(120, 60, 340, 25);
        getContentPane().add(txtAsunto);

        txtCuerpo = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtCuerpo);
        scrollPane.setBounds(20, 100, 440, 200);
        getContentPane().add(scrollPane);

        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        lblError.setBounds(20, 320, 300, 20);
        getContentPane().add(lblError);

        JButton btnEnviar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EnviarMensaje.Enviar"));
        btnEnviar.setBounds(330, 320, 130, 30);
        btnEnviar.addActionListener(e -> {
        	String destino = txtDestino.getText();
            String asunto = txtAsunto.getText();
            String cuerpo = txtCuerpo.getText();

            if (destino.isEmpty() || asunto.isEmpty() || cuerpo.isEmpty()) {
                lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("Mensaje.ErrorVacio"));
                return; 
            }
            
            if (destino.equals(miEmail)) {
                lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("Mensaje.ErrorMismo"));
                return; 
            }
            
            try {
                MainGUI.getBusinessLogic().enviarMensaje(
                    miEmail, 
                    txtDestino.getText(), 
                    txtAsunto.getText(), 
                    txtCuerpo.getText()
                );
                JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("Mensaje.Exito"));
                dispose(); 
            } catch (Exception ex) {
                lblError.setText(ex.getMessage()); 
            }
        });
        getContentPane().add(btnEnviar);
    }
}