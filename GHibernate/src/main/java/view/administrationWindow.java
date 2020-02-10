package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GHibernate.TableButton3;

public class administrationWindow extends JFrame {
	
	private static JFrame frame;
	private static JButton buttonClients;
	private static JButton buttonPedidos;
	private static JButton buttonFilms;
	private static JPanel controlPanel;
	
	public administrationWindow() {
				
		// Frame configuration
        frame = new JFrame();
        frame.setTitle("Videoclub - Administración");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		Dimension screenSize = myScreen.getScreenSize();
		
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		frame.setSize(screenWidth/2, screenHeight/2);
		frame.setLocation(screenWidth/4, screenHeight/4);
        frame.setResizable(false);
        
        controlPanel = new JPanel();
        
        buttonClients = new JButton("Administrar clientes");
        buttonClients.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		TableButton3 admin = new TableButton3();
        		frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        		admin.pack();
        		admin.setVisible(true);
        	}
        });
        
        buttonPedidos = new JButton("Realizar pedidos");
        buttonPedidos.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		initialWindow myWindow = new initialWindow();
        	}
        });
        
        buttonFilms = new JButton("Administrar películas");
        buttonFilms.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		TableButton3 admin = new TableButton3();
        		frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        		admin.pack();
        		admin.setVisible(true);
        	}
        });
        
        controlPanel.add(buttonClients);
        controlPanel.add(buttonPedidos);
        controlPanel.add(buttonFilms);
        
        frame.add(controlPanel);
        frame.setVisible(true);
	}

}
