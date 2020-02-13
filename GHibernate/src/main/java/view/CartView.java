package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClienteDAO;
import dao.PedidoDAO;
import model.Cliente;
import model.Pedido;
import model.PedidoLinea;
import model.Pelicula;

public class CartView extends JFrame{
	
	private static JFrame frame;
	private static JButton buttonAdd;
	private static JPanel panelView;
	private static JPanel container;
	private static JTable table;
	
	public CartView (ArrayList<Pelicula> peliculasSeleccionadas, Cliente user) {
		frame = new JFrame();
		panelView = new JPanel();
		container = new JPanel();
//        panelView.setPreferredSize(new Dimension(700, 100));
//        panelView.setBorder(BorderFactory.createLineBorder(Color.black));

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Id");
		model.addColumn("Titulo");
		model.addColumn("Precio");
		model.addColumn("Duracion");
		model.addColumn("Director");
		model.addColumn("Genero");

		for (Pelicula pelicula : peliculasSeleccionadas) {
//			String[] datosPelicula = new String[] { pelicula.getId().toString(), pelicula.getTitulo(), pelicula.getPrecio().toString(),
//					pelicula.getDuracion(), pelicula.getDirector(), pelicula.getGenero(), peli };

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
		panelView.add(scrollPane);
		
        buttonAdd = new JButton("Checkout");
        buttonAdd.addActionListener(new ActionListener () {
        	public void actionPerformed (ActionEvent e) {
        		realizarPedido(peliculasSeleccionadas, user);
        	}
        });
        
        
//        container.add(panelView);
        container.add(buttonAdd);
        
        add(BorderLayout.SOUTH, container);
        add(BorderLayout.NORTH, panelView);

	}
	
	public static void realizarPedido(ArrayList<Pelicula> peliculasSeleccionadas, Cliente user) {
				
		Pedido pedido = new Pedido(user);
//		pedido.setCliente(user);
		
		List<PedidoLinea> lineas = new ArrayList<PedidoLinea>();
		
		for (Pelicula pelicula: peliculasSeleccionadas) {
			
			PedidoLinea nuevaLinea = new PedidoLinea(pedido);
			nuevaLinea.setArticulo(pelicula);
			nuevaLinea.setUnidades(new BigDecimal(1));
			nuevaLinea.setPrecio(pelicula.getPrecio());
			BigDecimal unidades = nuevaLinea.getUnidades();
//			unidades = unidades.add(new BigDecimal(1));
			nuevaLinea.setUnidades(unidades);
			lineas.add(nuevaLinea);
			System.out.println(user.getId());
			
			
			
			
		}
		frame.setVisible(false);
		JOptionPane.showMessageDialog(null, "Pedido Realizado");
		PedidoDAO.InsertarPedido(pedido, user);
		
		
	}

}
