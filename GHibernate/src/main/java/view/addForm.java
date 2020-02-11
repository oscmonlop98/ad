package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addForm extends JFrame{
	
	private static JFrame frame;
	private static JPanel panelView;
	private static JLabel userName;
	private static JLabel password;
	private static JTextField userField;
	private static JTextField passwordField;
	
	
	public addForm() {
		
		frame = new JFrame();
		frame.setTitle("Formulario añadir cliente");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelView = new JPanel();
		
		userName = new JLabel("Introduce usuario:");
		userField = new JTextField();
		password = new JLabel("Introduce contraseña:");
		passwordField = new JTextField();
		
		panelView.add(userName);
		panelView.add(userField);
		panelView.add(password);
		panelView.add(passwordField);
		
		frame.add(panelView);
		frame.setVisible(true);	
		
	}

}
