package view;

import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.UnidadPersistencia;
import dao.ClienteDAO;
import model.Cliente;

public class adminClient extends JFrame{
	
	public adminClient() {

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
        getContentPane().add( scrollPane );
        
        
        
        
	}
	

}
