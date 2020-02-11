package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.UnidadPersistencia;
import dao.ClienteDAO;
import model.Cliente;

public class adminClient extends JFrame {

	private static JFrame frame;
	private static JButton buttonAdd;
	private static JPanel panelView;

	public adminClient() {
		
		frame = new JFrame();
		frame.getContentPane();

		DefaultTableModel model = new DefaultTableModel();

//        JTable table = new JTable(model);
		model.addColumn("Id");
		model.addColumn("Nombre");
		model.addColumn("Seleccionar");

		String[] arrayClientes;
		List<Cliente> clientes = ClienteDAO.getClientes();

		for (Cliente cliente : clientes) {
			String[] datosCliente = new String[] { cliente.getId().toString(), cliente.getNombre() };

			Object[] data = new Object[] { cliente.getId().toString(), cliente.getNombre(), false };
			model.addRow(data);
		}
		JTable table = new JTable(model) {

			private static final long serialVersionUID = 1L;

			/*
			 * @Override public Class getColumnClass(int column) { return getValueAt(0,
			 * column).getClass(); }
			 */
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
		getContentPane().add(scrollPane);
		
        buttonAdd = new JButton("Añadir clientes");
        buttonAdd.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		addForm a = new addForm();
        		a.setVisible(true);
        	}
        });

	}

}
