package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClienteDAO;
import dao.PedidoDAO;
import model.Cliente;

public class checkoutWindow extends JFrame{
	
	private static JFrame frame;
	private static JPanel actionPanel;
	private static JPanel tablePanel;
	private static JButton buttonPedido;
	
	
	public static void initComponents() {
		frame = new JFrame();
        frame.setTitle("Videoclub - Checkout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        actionPanel = new JPanel();
        buttonPedido = new JButton("Realizar pedido");
        buttonPedido.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		PedidoDAO.InsertarPedido();
        		// Pasar los parametros necesarios para realizar el pedido.
        	}
        });
        
        checkoutWindow();
        
        actionPanel.add(buttonPedido);
        frame.add(actionPanel);
        
	}

	public static void checkoutWindow() {
		
		DefaultTableModel model = new DefaultTableModel();

		JTable table = new JTable(model);
		model.addColumn("Id");
		model.addColumn("Nombre");

		String[] arrayClientes;
		List<Cliente> clientes = ClienteDAO.getClientes();

		for (Cliente cliente : clientes) {
			String[] datosCliente = new String[] { cliente.getId().toString(), cliente.getNombre() };
			model.addRow(datosCliente);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel = new JPanel();
		frame.add(tablePanel);
		frame.getContentPane().add(scrollPane);
	}
}
