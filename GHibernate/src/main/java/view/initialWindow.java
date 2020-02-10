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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.PedidoDAO;


public class initialWindow extends JFrame {
	
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
	
	
	public initialWindow() {
		
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
        titleLabel = new JLabel("ALQUILER DE PEL�CULAS");
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 26));
        container.add(BorderLayout.BEFORE_FIRST_LINE, titleLabel);
        
        
        viewPanel = new JPanel();
        viewPanel.setPreferredSize(new Dimension(700, 100));
        viewPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        String[] columnNames = {"Date", "String", "Integer", "Decimal", ""};
        Object[][] data =
        {
            {new Date(), "A", new Integer(1), new Double(5.1), "A"},
            {new Date(), "B", new Integer(2), new Double(6.2), "Delete1"},
            {new Date(), "C", new Integer(3), new Double(7.3), "Delete2"},
            {new Date(), "D", new Integer(4), new Double(8.4), "Delete3"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable( model )
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        
        viewPanel.add(table);

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
