package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.ClienteDAO;
import dao.PeliculaDAO;
import model.Pelicula;

public class AddFormFilm extends JFrame{
	
	private static JFrame frame;
	private static JPanel panelView;
	private static JLabel labelTitulo;
	private static JLabel labelPrecio;
	private static JLabel labelDuracion;
	private static JLabel labelDirector;
	private static JLabel labelGenero;
	private static JTextField fieldTitulo;
	private static JTextField fieldPrecio;
	private static JTextField fieldDuracion;
	private static JTextField fieldDirector;
	private static JTextField fieldGenero;
	private static JButton buttonAdd;
	
	public AddFormFilm() {
		
		setSize(260,350);
		setTitle("Formulario añadir pelicula");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelView = new JPanel();
		
		labelTitulo = new JLabel("Titulo: ");
		labelTitulo.setBorder(BorderFactory.createEmptyBorder(10,0,10,25));
		fieldTitulo = new JTextField();
		fieldTitulo.setPreferredSize(new Dimension(150,20));
		
		labelPrecio = new JLabel("Precio: ");
		labelPrecio.setBorder(BorderFactory.createEmptyBorder(10,0,10,22));
		fieldPrecio = new JTextField();
		fieldPrecio.setPreferredSize(new Dimension(150,20));
		
		labelDuracion = new JLabel("Duracion: ");
		labelDuracion.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
		fieldDuracion = new JTextField();
		fieldDuracion.setPreferredSize(new Dimension(150,20));
		
		labelDirector = new JLabel("Director: ");
		labelDirector.setBorder(BorderFactory.createEmptyBorder(10,0,10,20));
		fieldDirector = new JTextField();
		fieldDirector.setPreferredSize(new Dimension(150,20));
		
		labelGenero = new JLabel("Genero: ");
		labelGenero.setBorder(BorderFactory.createEmptyBorder(10,0,10,25));
		fieldGenero = new JTextField();
		fieldGenero.setPreferredSize(new Dimension(150,20));
		
		buttonAdd = new JButton("Añadir");
		buttonAdd.setPreferredSize(new Dimension(220,30));
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pelicula pelicula = new Pelicula();
				
				pelicula.setTitulo(fieldTitulo.getText());
				BigDecimal precio = new BigDecimal(fieldPrecio.getText());
				pelicula.setPrecio(precio);
				pelicula.setDuracion(fieldDuracion.getText());
				pelicula.setDirector(fieldDirector.getText());
				pelicula.setGenero(fieldGenero.getText());
				
				PeliculaDAO.InsertarPelicula(pelicula);
			}
		});
		
		panelView.add(BorderLayout.LINE_START, labelTitulo);
		panelView.add(BorderLayout.LINE_END, fieldTitulo);
		panelView.add(BorderLayout.SOUTH, labelPrecio);
		panelView.add(BorderLayout.LINE_END,fieldPrecio);
		panelView.add(BorderLayout.SOUTH, labelDuracion);
		panelView.add(BorderLayout.LINE_END,fieldDuracion);
		panelView.add(BorderLayout.SOUTH, labelDirector);
		panelView.add(BorderLayout.LINE_END,fieldDirector);
		panelView.add(BorderLayout.SOUTH, labelGenero);
		panelView.add(BorderLayout.LINE_END,fieldGenero);
		panelView.add(BorderLayout.CENTER, buttonAdd);
		panelView.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(panelView);
		setVisible(true);	
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

}
