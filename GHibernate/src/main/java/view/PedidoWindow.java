package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.PeliculaDAO;
import model.Cliente;
import model.Pelicula;


public class PedidoWindow extends JFrame {
	
	private static JFrame frame;
	private static JPanel container;
	private static JPanel viewPanel;
	private static JPanel controlPanel;
	private static JLabel titleLabel;
	private static JLabel userLabel;
	private static JButton buttonLogin;
	private static JButton buttonCart;
	private static JButton buttonAdd;
	private static JButton buttonManagement;
	private static JTable table;
	private static List<Pelicula> peliculas;
	private static ArrayList<String> titulos; 
	
	
	public PedidoWindow() {
		
		// Frame configuration
        frame = new JFrame();
        frame.setTitle("Videoclub");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		Dimension screenSize = myScreen.getScreenSize();
		
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		frame.setSize(screenWidth/2, screenHeight/2);
		frame.setLocation(screenWidth/4, screenHeight/4);
        frame.setResizable(false);
		
		// Container initial configuration
        container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Elements container
        titleLabel = new JLabel("ALQUILER DE PELICULAS");
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 26));
        container.add(BorderLayout.BEFORE_FIRST_LINE, titleLabel);
        
        
        viewPanel = new JPanel();
        viewPanel.setPreferredSize(new Dimension(700, 100));
        viewPanel.setBorder(BorderFactory.createLineBorder(Color.black));

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
        viewPanel.add( scrollPane );
        
        

        container.add(BorderLayout.WEST, viewPanel);
        
        
        
        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(200, 800));
        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		System.out.println("Hola");
        		Login login = new Login();
        		login.start();
        		
			}
        });
        
        buttonCart = new JButton("Cart");
        buttonCart.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		CartView cart = new CartView();
        		cart.setDefaultCloseOperation( EXIT_ON_CLOSE );
        		cart.pack();
        		cart.setVisible(true);
        	}
        });
        
        buttonAdd = new JButton("Add to Cart");
        buttonAdd.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		addToCart(table);
        		List<Pelicula> peliculasSeleccionadas = new ArrayList<Pelicula>();
        		for (String titulo : titulos) {
        			for (Pelicula pelicula : peliculas) {
        				String datosPelicula = pelicula.getTitulo();
        				if(datosPelicula == titulo) {
        					peliculasSeleccionadas.add(pelicula);
        				}
        			}
        		}  
        	}
        });
        
        buttonManagement = new JButton("Gestion de pedidos");
        buttonManagement.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		PedidoManagement gestion = new PedidoManagement();
        		gestion.setDefaultCloseOperation(EXIT_ON_CLOSE);
        		gestion.pack();
        		gestion.setVisible(true);
        	}
        });
        
        controlPanel.add(BorderLayout.NORTH, buttonLogin);
        controlPanel.add(BorderLayout.NORTH, buttonCart);
        controlPanel.add(BorderLayout.SOUTH, buttonAdd);
        controlPanel.add(BorderLayout.SOUTH, buttonManagement);
        container.add(BorderLayout.EAST, controlPanel);
        
        frame.getContentPane().add(BorderLayout.CENTER, container);
        frame.setVisible(true);

	}
	
	
	public static void initializeLogin() {
		userLabel.setText("Holaaaaaaaaaaaaaaaaaaaaaaaa");
		container.add(BorderLayout.WEST, userLabel);
	}
	
	public static void addToCart(JTable tabla) {
		
		TableModel model = tabla.getModel();
		
	    for (int i = 0; i <= model.getRowCount() - 1; i++) {    
	        if ((Boolean) model.getValueAt(i, 6)) {
	           titulos.add(model.getValueAt(i, 1).toString());
	        }
	    }
		
	}
	
	public static void doCheckout() {
		
	}

}
