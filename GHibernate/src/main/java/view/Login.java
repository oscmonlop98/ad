package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.ClienteDAO;
import view.initialWindow;

public class Login {
	
	private static JFrame frame;
	private static JLabel labelLogin;
	private static JTextField entryLogin;
	private static JButton buttonLogin;
	private static JLabel labelPassword;
	private static JTextField entryPassword;
	
	public void initComponents() {
		
		frame = new JFrame();
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        addComponentsToPane(frame.getContentPane());
        
        frame.setSize(new Dimension(400,300));
		frame.setPreferredSize(new Dimension(400, 300));
		
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	    frame.setVisible(true);
	}
	
	public static void addComponentsToPane(Container pane) {

		pane.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;

		
		labelLogin = new JLabel("Nombre de usuario:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(50,50,0,50);
		pane.add(labelLogin, c);

		entryLogin = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0,50,0,50);
		pane.add(entryLogin, c);
		
		labelPassword= new JLabel("Contraseña:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(50,50,0,50);
		pane.add(labelPassword, c);

		entryPassword = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0,50,0,50);
		pane.add(entryPassword, c);
		
		buttonLogin = new JButton("Iniciar sesion");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(0,50,50,50);
		
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				login();
			}
		});
		pane.add(buttonLogin, c);
	}
	
	public void start() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initComponents();
			}
		});
	}
	
	public static void login() {
		String username = "";
		String password = "";
		username = entryLogin.getText();
		password = entryPassword.getText();
		
		ClienteDAO.InsertarCliente(username, password);
		
		System.out.println(username + " " + password);
	}

}
