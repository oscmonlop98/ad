package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.UnidadPersistencia;
import dao.ClienteDAO;
import dao.PeliculaDAO;
import model.Cliente;
import model.Pelicula;

public class ManageFilm extends JFrame {
	
	private static JFrame frame;
	private static JButton buttonAdd;
	private static JButton buttonDelete;
	private static JPanel panelView;
	private static JPanel container;
	private static List<Pelicula> peliculas;
	private static ArrayList<String> titulos;
	private static JTable table;
	
	public ManageFilm() {
		
		frame = new JFrame();
		panelView = new JPanel();
		container = new JPanel();

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Id");
		model.addColumn("Titulo");
		model.addColumn("Precio");
		model.addColumn("Duracion");
		model.addColumn("Director");
		model.addColumn("Genero");
		model.addColumn("Seleccionar");

		titulos = new ArrayList<String>();
		peliculas = PeliculaDAO.getPeliculas();

		for (Pelicula pelicula : peliculas) {
			String[] datosPelicula = new String[] { pelicula.getId().toString(), pelicula.getTitulo() };

			Object[] data = new Object[] { pelicula.getId().toString(), pelicula.getTitulo(), pelicula.getPrecio().toString(),
					pelicula.getDuracion(), pelicula.getDirector(), pelicula.getGenero(), false };
			model.addRow(data);
		}
		table = new JTable(model) {

			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				default:
					return Boolean.class;
				}
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(870,280));
		panelView.add(scrollPane);
		
        buttonAdd = new JButton("Añadir peliculas");
        buttonAdd.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		AddFormFilm a = new AddFormFilm();
        		a.setVisible(true);
        		setVisible(false);
        	}
        });
        
        buttonDelete = new JButton("Eliminar peliculas");
        buttonDelete.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		devuelveSeleccionados(table);
        		
        		for (String titulo : titulos) {
        			for (Pelicula pelicula : peliculas) {
        				String datosPelicula = pelicula.getTitulo();
        				if(datosPelicula == titulo) {
        					PeliculaDAO.eliminarPelicula(pelicula);
        					
        					JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente");
        					setVisible(false);
        					ManageFilm nueva = new ManageFilm();
        					nueva.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        	        		nueva.pack();
        					nueva.setVisible(true);
        				}
        			}
        		}    
        	}
        });
        
        container.add(buttonAdd);
        container.add(buttonDelete);
        
        add(BorderLayout.NORTH, panelView);
        add(BorderLayout.SOUTH, container);
        setPreferredSize(new Dimension(900,360));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	
	public static void devuelveSeleccionados(JTable tabla) {
		TableModel model = tabla.getModel();
		
	    for (int i = 0; i <= model.getRowCount() - 1; i++) {    
	        if ((Boolean) model.getValueAt(i, 6)) {
	           titulos.add(model.getValueAt(i, 1).toString());
	        }
	    }
	}
	
	

}
