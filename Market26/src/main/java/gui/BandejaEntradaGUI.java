package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.ResourceBundle;

import domain.Mensaje;
import businessLogic.BLFacade;

public class BandejaEntradaGUI extends JFrame {
    private JTable tableMensajes;
    private DefaultTableModel tableModel;
    private String miEmail;
    private List<Mensaje> misMensajes;

    public BandejaEntradaGUI(String email) {
        this.miEmail = email;
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("BandejaEntrada.Titulo"));
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(null);

        JButton btnNuevo = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BandejaEntrada.NuevoMensaje"));
        btnNuevo.setBounds(20, 20, 150, 30);
        btnNuevo.addActionListener(e -> {
            new EnviarMensajeGUI(miEmail).setVisible(true);
        });
        getContentPane().add(btnNuevo);

        String[] columnNames = {
        	    "ID",
        	    ResourceBundle.getBundle("Etiquetas").getString("BandejaEntrada.Emisor"),
        	    ResourceBundle.getBundle("Etiquetas").getString("BandejaEntrada.Asunto"),
        	    ResourceBundle.getBundle("Etiquetas").getString("BandejaEntrada.Fecha")
        };
        tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableMensajes = new JTable(tableModel);

        tableMensajes.getColumnModel().getColumn(0).setMinWidth(0);
        tableMensajes.getColumnModel().getColumn(0).setMaxWidth(0);

        JScrollPane scrollPane = new JScrollPane(tableMensajes);
        scrollPane.setBounds(20, 60, 540, 280);
        getContentPane().add(scrollPane);

        cargarMensajes();

        tableMensajes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tableMensajes.getSelectedRow();
                    if (row != -1) {
                    	Mensaje msgSeleccionado = misMensajes.get(row);
                    	new LeerMensajeGUI(miEmail, msgSeleccionado).setVisible(true);
                    }
                }
            }
        });
    }

    private void cargarMensajes() {
        BLFacade facade = MainGUI.getBusinessLogic();
        misMensajes = facade.getMensajesRecibidos(miEmail);
        
        tableModel.setRowCount(0); 
        for (Mensaje m : misMensajes) {
            Object[] row = {
                m.getId(), 
                m.getEmisor().getEmail(), 
                m.getAsunto(), 
                m.getFechaEnvio().toString()
            };
            tableModel.addRow(row);
        }
    }
}