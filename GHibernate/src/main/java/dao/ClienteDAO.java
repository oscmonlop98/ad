package dao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

import controller.UnidadPersistencia;
import model.Cliente;

public class ClienteDAO {
	
	public static void insertarCliente (String nombre, String password){
		
		Cliente cliente = new Cliente ();
		cliente.setNombre(nombre);
		cliente.setContrasenya(password);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();
		
		UnidadPersistencia.getInstance().getEntityManager().persist(cliente);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();

	}
	
	public static List<Cliente> getClientes() {
		List<Cliente> clientes = UnidadPersistencia.getInstance().getEntityManager().createQuery("from Cliente order by Id", Cliente.class).getResultList();
		return clientes;
	}
	
//	public static void show() {
//		
//		DefaultTableModel model = new DefaultTableModel();
//		String[] arrayClientes;
//
//		List<Cliente> clientes = UnidadPersistencia.getInstance().getEntityManager().createQuery("from Cliente order by Id", Cliente.class).getResultList();
//		
//		for (Cliente cliente : clientes) {
//			String[] datosCliente = new String[] {
//					cliente.getId().toString(), cliente.getNombre()
//			};
//			model.addRow(datosCliente);
//			
//		}
//					
//	}
	
	public static void eliminarCliente (Cliente cliente){
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();
		
		UnidadPersistencia.getInstance().getEntityManager().remove(cliente);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();
		System.out.println("ELIMINADO");

	}
	
	public static boolean getUser(String name, String password) {
		boolean resultado = false;
		System.out.println("Estoy en el DAO " + name + " " + password);
		List<Cliente> clientes = UnidadPersistencia.getInstance().getEntityManager().createQuery(
				"from Cliente where nombre='" + name + "' AND contrasenya='" + password + "'" , Cliente.class).getResultList();
		if(clientes.size() == 1)
			resultado = true;
			
		return resultado;
	}

}