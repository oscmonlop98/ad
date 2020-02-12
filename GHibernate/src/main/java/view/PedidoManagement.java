package view;

import java.awt.BorderLayout;
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
import model.Cliente;

public class PedidoManagement extends JFrame{
	private static JFrame frame;
	private static JButton buttonDelete;
	private static JPanel panelView;
	private static JPanel container;
	
	public PedidoManagement () {
		frame = new JFrame();
		panelView = new JPanel();
		container = new JPanel();
//        panelView.setPreferredSize(new Dimension(700, 100));
//        panelView.setBorder(BorderFactory.createLineBorder(Color.black));

		DefaultTableModel model = new DefaultTableModel();

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
        
        buttonDelete = new JButton("Eliminar pedido");
        buttonDelete.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		//Eliminar usuarios seleccionados
        	}
        });
        
        container.add(panelView);
        container.add(buttonDelete);
        
        add(BorderLayout.CENTER, container);

	}

}