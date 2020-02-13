package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.UnidadPersistencia;
import dao.ClienteDAO;
import dao.PedidoDAO;
import model.Cliente;
import model.Pedido;

public class PedidoManagement extends JFrame{
	private static JFrame frame;
	private static JButton buttonDelete;
	private static JPanel panelView;
	private static JPanel container;
	private static JTable table;
	private static ArrayList<String> idPedidos;
	private static List<Pedido> pedidos;
	
	public PedidoManagement () {
		frame = new JFrame();
		panelView = new JPanel();
		container = new JPanel();
//        panelView.setPreferredSize(new Dimension(700, 100));
//        panelView.setBorder(BorderFactory.createLineBorder(Color.black));

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Id");
		model.addColumn("Fecha");
		model.addColumn("Importe");
		model.addColumn("Cliente");
		model.addColumn("Seleccionar");

		idPedidos = new ArrayList<String>();
		
		pedidos = PedidoDAO.getPedidos();

		for (Pedido pedido : pedidos) {
//			String[] datosCliente = new String[] { cliente.getId().toString(), cliente.getNombre() };

			Object[] data = new Object[] { pedido.getId().toString(), pedido.getFecha(), pedido.getImporte(), pedido.getCliente().getNombre(), false };
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
        		devuelveSeleccionados(table);
        		
        		for (String idPedido : idPedidos) {
        			for (Pedido pedido1 : pedidos) {
        				String datosPedido = pedido1.getId().toString();
        				System.out.println("DATOS PEDIDO:" + datosPedido);
        				System.out.println("IDPEDIDO" +idPedido);
        				if(datosPedido.equals(idPedido)) {
        					System.out.println("HOLAAA");
        					PedidoDAO.eliminarPedido(pedido1);
        				}
        			}
        		}    
        	}
        	
        });
        
        container.add(buttonDelete);
        
        add(BorderLayout.SOUTH, container);
        add(BorderLayout.NORTH, panelView);

	}
	
	public static void devuelveSeleccionados(JTable tabla) {
		TableModel model = tabla.getModel();
		
	    for (int i = 0; i <= model.getRowCount() - 1; i++) {    
	        if ((Boolean) model.getValueAt(i, 4)) {
	        	idPedidos.add(model.getValueAt(i, 0).toString());
	        }
	    }
	}
}
