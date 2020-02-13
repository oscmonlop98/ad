package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Cliente;


public class AdministrationWindow extends JFrame {
	
	private static JFrame frame;
	private static JButton buttonClients;
	private static JButton buttonPedidos;
	private static JButton buttonFilms;
	private static JPanel controlPanel;
	private static JPanel labelPanel;
	private static JLabel bienvenidos;
	
	public AdministrationWindow() {
				
		// Frame configuration
        frame = new JFrame();
        frame.setTitle("Videoclub - Administracion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		Dimension screenSize = myScreen.getScreenSize();
		
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		frame.setSize(screenWidth/2, screenHeight/2);
		frame.setLocation(screenWidth/4, screenHeight/4);
        frame.setResizable(false);
        
        controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(30,10,30,10));
        labelPanel = new JPanel();
        labelPanel.setBorder(BorderFactory.createEmptyBorder(60,10,30,10));
        
        bienvenidos = new JLabel("Bienvenido al Videoclub");
        bienvenidos.setFont(new Font("Courier New", Font.BOLD, 46));
        
        buttonClients = new JButton("Administrar clientes");
        buttonClients.setPreferredSize(new Dimension(210,100));
        buttonClients.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		ManageClient manageClient = new ManageClient();
        		manageClient.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        		manageClient.pack();
        		manageClient.setVisible(true);
        	}
        });
        
        buttonPedidos = new JButton("Realizar pedidos");
        buttonPedidos.setPreferredSize(new Dimension(210,100));
        buttonPedidos.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		Cliente cliente = new Cliente();
        		PedidoWindow myWindow = new PedidoWindow(false, cliente);
        		myWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        	}
        });
        
        buttonFilms = new JButton("Administrar peliculas");
        buttonFilms.setPreferredSize(new Dimension(210,100));
        buttonFilms.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		ManageFilm admin = new ManageFilm();
        		admin.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        		admin.pack();
        		admin.setVisible(true);
        	}
        });
        
        labelPanel.add(BorderLayout.CENTER, bienvenidos);
        controlPanel.add(buttonClients);
        controlPanel.add(buttonPedidos);
        controlPanel.add(buttonFilms);
        
        frame.add(BorderLayout.NORTH, labelPanel);
        frame.add(controlPanel);
        frame.setVisible(true);
	}

}
