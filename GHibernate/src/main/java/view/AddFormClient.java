package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.ClienteDAO;

public class AddFormClient extends JFrame{
	
	private static JFrame frame;
	private static JPanel panelView;
	private static JLabel userName;
	private static JLabel password;
	private static JTextField userField;
	private static JPasswordField passwordField;
	private static JButton buttonAdd;
	
	public AddFormClient() {
		
		setSize(300,200);
		setTitle("Formulario añadir cliente");
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		panelView = new JPanel();
		
		userName = new JLabel("Nombre del cliente:");
		userName.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		userField = new JTextField();
		userField.setPreferredSize(new Dimension(120,20));
		password = new JLabel("Introduce contraseña:");
		passwordField = new JPasswordField(10);
		
		buttonAdd = new JButton("Añadir");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username, contrasenya;
				username = userField.getText();
				contrasenya = passwordField.getText();
				System.out.println("Nombre: " + username + " contraseña: " + contrasenya);
				ClienteDAO.insertarCliente(username, contrasenya);
			}
		});
		
		panelView.add(BorderLayout.LINE_START, userName);
		panelView.add(BorderLayout.LINE_END, userField);
		panelView.add(BorderLayout.LINE_START, password);
		panelView.add(BorderLayout.LINE_END, passwordField);
		panelView.add(BorderLayout.SOUTH, buttonAdd);
		
		add(panelView);
		setVisible(true);	
		
	}

}
