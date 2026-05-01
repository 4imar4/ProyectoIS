package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import domain.Mensaje;

public class LeerMensajeGUI extends JFrame {
    private String miEmail;
    private Mensaje msgActual;

    public LeerMensajeGUI(String miEmail, Mensaje msg) {
        this.miEmail = miEmail;
        this.msgActual = msg;
        
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("LeerMensaje.Titulo"));
        setBounds(150, 150, 500, 400);
        getContentPane().setLayout(null);

        JLabel lblDe = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LeerMensaje.De") + msgActual.getEmisor().getEmail());
        lblDe.setBounds(20, 20, 400, 20);
        getContentPane().add(lblDe);

        JLabel lblAsunto = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LeerMensaje.Asunto") + msgActual.getAsunto());
        lblAsunto.setBounds(20, 50, 400, 20);
        getContentPane().add(lblAsunto);

        JTextArea txtCuerpo = new JTextArea(msgActual.getCuerpo());
        txtCuerpo.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(txtCuerpo);
        scrollPane.setBounds(20, 80, 440, 220);
        getContentPane().add(scrollPane);

        JButton btnResponder = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LeerMensaje.Responder"));
        btnResponder.setBounds(330, 320, 130, 30);
        btnResponder.addActionListener(e -> {
            String destino = msgActual.getEmisor().getEmail();
            String asuntoRep = "RE: " + msgActual.getAsunto();
            new EnviarMensajeGUI(miEmail, destino, asuntoRep).setVisible(true);
            dispose(); 
        });
        getContentPane().add(btnResponder);
    }
}