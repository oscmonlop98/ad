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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
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
	private static ArrayList<Pelicula> peliculasSeleccionadas;
	private static JPanel panelTitulo;
	private static JLabel labelUser;

	public PedidoWindow(boolean userLogued, Cliente user) {

		// Frame configuration
		frame = new JFrame();
		frame.setTitle("Videoclub");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		peliculasSeleccionadas = new ArrayList<Pelicula>();

		Toolkit myScreen = Toolkit.getDefaultToolkit();
		Dimension screenSize = myScreen.getScreenSize();

		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		frame.setSize(screenWidth / 2, screenHeight / 2);
		frame.setLocation(screenWidth / 4, screenHeight / 4);
		frame.setResizable(false);

		// Container initial configuration
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Elements container
		panelTitulo = new JPanel();
		titleLabel = new JLabel("ALQUILER DE PELICULAS");
		titleLabel.setFont(new Font("Courier New", Font.BOLD, 26));
		panelTitulo.add(titleLabel);

		controlPanel = new JPanel();
		viewPanel = new JPanel();
		viewPanel.setPreferredSize(new Dimension(300, 50));
//        viewPanel.setBorder(BorderFactory.createLineBorder(Color.black));

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

			Object[] data = new Object[] { pelicula.getId().toString(), pelicula.getTitulo(),
					pelicula.getPrecio().toString(), pelicula.getDuracion(), pelicula.getDirector(),
					pelicula.getGenero(), false };
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
		scrollPane.setPreferredSize(new Dimension(690, 410));
		viewPanel.add(scrollPane);

		container.add(BorderLayout.CENTER, viewPanel);

		controlPanel.setPreferredSize(new Dimension(200, 150));
		buttonLogin = new JButton("Login");
		buttonLogin.setPreferredSize(new Dimension(200, 40));

		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola");
				Login login = new Login();
				login.start();
				frame.setVisible(false);
			}
		});

		buttonCart = new JButton("Cart");
		buttonCart.setPreferredSize(new Dimension(200, 40));
		buttonCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (peliculasSeleccionadas.size() > 0) {
					CartView cart = new CartView(peliculasSeleccionadas, user);
					cart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					cart.pack();
					cart.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "El carro está vacío!");
				}

			}
		});

		buttonAdd = new JButton("Add to Cart");
		buttonAdd.setPreferredSize(new Dimension(200, 40));
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCart(table);
				peliculasSeleccionadas.clear();
				for (String titulo : titulos) {
					for (Pelicula pelicula : peliculas) {
						String datosPelicula = pelicula.getTitulo();
						if (datosPelicula == titulo) {
							peliculasSeleccionadas.add(pelicula);
							
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Peliculas añadidas al carro");
			}
		});

		buttonManagement = new JButton("Gestion de pedidos");
		buttonManagement.setPreferredSize(new Dimension(200, 40));
		buttonManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoManagement gestion = new PedidoManagement();
				gestion.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				gestion.pack();
				gestion.setVisible(true);
			}
		});
		labelUser = new JLabel();
		if (userLogued) {
			labelUser.setText("Usuario conectado: " + user.getNombre());
			controlPanel.add(BorderLayout.NORTH, labelUser);
			buttonLogin.setVisible(false);
			JButton buttonLogOut = new JButton("Log Out");
			buttonLogOut.setPreferredSize(new Dimension(200, 40));
			buttonLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					labelUser.setText("");
					labelUser.setVisible(false);
					buttonLogOut.setVisible(false);
					buttonLogin.setVisible(true);
				}
			});
			controlPanel.add(BorderLayout.NORTH, buttonLogOut);

		}

		container.add(BorderLayout.NORTH, panelTitulo);
		controlPanel.add(BorderLayout.NORTH, buttonLogin);
		controlPanel.add(BorderLayout.NORTH, buttonCart);
		controlPanel.add(BorderLayout.SOUTH, buttonAdd);
		controlPanel.add(BorderLayout.SOUTH, buttonManagement);
		container.add(BorderLayout.EAST, controlPanel);

		frame.getContentPane().add(container);
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
