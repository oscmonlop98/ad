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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.UnidadPersistencia;
import dao.ClienteDAO;
import model.Cliente;

public class ManageClient extends JFrame {

	private static JFrame frame;
	private static JButton buttonAdd;
	private static JButton buttonDelete;
	private static JPanel panelView;
	private static JPanel container;
	private static JTable table;
	private static ArrayList<String> nombres;
	private static List<Cliente> clientes;

	public ManageClient() {
		
		frame = new JFrame();
		panelView = new JPanel();
		container = new JPanel();

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Id");
		model.addColumn("Nombre");
		model.addColumn("Seleccionar");

		nombres = new ArrayList<String>();
		
		clientes = ClienteDAO.getClientes();

		for (Cliente cliente : clientes) {
			
			String[] datosCliente = new String[] { cliente.getId().toString(), cliente.getNombre() };

			Object[] data = new Object[] { cliente.getId().toString(), cliente.getNombre(), false };
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
				default:
					return Boolean.class;
				}
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		panelView.add(scrollPane);
		
        buttonAdd = new JButton("Añadir clientes");
        buttonAdd.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		AddFormClient a = new AddFormClient();
        		a.setVisible(true);
        	}
        });
        
        buttonDelete = new JButton("Eliminar cliente");
        buttonDelete.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		devuelveSeleccionados(table);
        		
        		for (String nombre : nombres) {
        			for (Cliente cliente : clientes) {
        				String datosCliente = cliente.getNombre();
        				if(datosCliente == nombre) {
        					ClienteDAO.eliminarCliente(cliente);
        				}
        			}
        		}        		
        	}
        });
        
        container.add(panelView);
        container.add(buttonAdd);
        container.add(buttonDelete);
        
        add(BorderLayout.CENTER, container);

	}
	
	public static void devuelveSeleccionados(JTable tabla) {
		TableModel model = tabla.getModel();
		
	    for (int i = 0; i <= model.getRowCount() - 1; i++) {    
	        if ((Boolean) model.getValueAt(i, 2)) {
	           nombres.add(model.getValueAt(i, 1).toString());
	        }
	    }
	}

}
