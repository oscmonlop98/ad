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
import javax.swing.table.DefaultTableModel;

import dao.ClienteDAO;
import dao.PedidoDAO;
import model.Cliente;


public class pedidoWindow extends JFrame {
	
	private static JFrame frame;
	private static JPanel container;
	private static JPanel viewPanel;
	private static JPanel controlPanel;
	private static JLabel titleLabel;
	private static JLabel userLabel;
	private static JButton buttonLogin;
	private static JButton buttonCart;
	private static JButton buttonAdd;
	private static JButton buttonCheckout;
	
	
	public pedidoWindow() {
		
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
        
        JTable table = new JTable(model);
        model.addColumn("Id");
        model.addColumn("Nombre");
        
        String[] arrayClientes;
        List <Cliente> clientes = ClienteDAO.getClientes();
		
		for (Cliente cliente : clientes) {
			String[] datosCliente = new String[] {
					cliente.getId().toString(), cliente.getNombre()
			};
			model.addRow(datosCliente);
		}

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
        buttonAdd = new JButton("Add to Cart");
        buttonAdd.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		addToCart();
        	}
        });
        buttonCheckout = new JButton("Checkout");
        buttonCheckout.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		//Iniciar la ventana de pedidos.
        	}
        });
        
        controlPanel.add(BorderLayout.NORTH, buttonLogin);
        controlPanel.add(BorderLayout.NORTH, buttonCart);
        controlPanel.add(BorderLayout.SOUTH, buttonAdd);
        controlPanel.add(BorderLayout.SOUTH, buttonCheckout);
        container.add(BorderLayout.EAST, controlPanel);
        
        frame.getContentPane().add(BorderLayout.CENTER, container);
        frame.setVisible(true);

	}
	
	
	public static void initializeLogin() {
		userLabel.setText("Holaaaaaaaaaaaaaaaaaaaaaaaa");
		container.add(BorderLayout.WEST, userLabel);
	}
	
	public static void addToCart() {
		
		PedidoDAO.InsertarPedido();
		
	}
	
	public static void doCheckout() {
		
	}

}
