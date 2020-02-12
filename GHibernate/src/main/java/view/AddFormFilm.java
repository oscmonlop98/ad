package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.ClienteDAO;
import dao.PeliculaDAO;

public class AddFormFilm extends JFrame{
	
	private static JFrame frame;
	private static JPanel panelView;
	private static JLabel titulo;
	private static JTextField peliculaField;
	private static JButton buttonAdd;
	
	public AddFormFilm() {
		
		setSize(500,500);
		setTitle("Formulario añadir pelicula");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelView = new JPanel();
		
		titulo = new JLabel("Introduce el titulo pelicula:");
		peliculaField = new JTextField();
		peliculaField.setPreferredSize(new Dimension(50,20));
		
		buttonAdd = new JButton("Añadir");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloPelicula;
				tituloPelicula = peliculaField.getText();
				PeliculaDAO.InsertarPelicula(tituloPelicula);
			}
		});
		
		panelView.add(titulo);
		panelView.add(peliculaField);
		panelView.add(buttonAdd);
		
		add(panelView);
		setVisible(true);	
		
	}

}
