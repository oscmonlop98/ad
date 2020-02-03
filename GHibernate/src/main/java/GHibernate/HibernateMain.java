package GHibernate;


import java.util.List;
import java.util.Scanner;

import controller.UnidadPersistencia;
import dao.ClienteDAO;
import model.Cliente;

public class HibernateMain {

	public static void main(String[] args) {
		String nombre = "";
		Scanner tcl = new Scanner(System.in);
		
		System.out.println("Introduce el nombre del cliente: ");
		nombre = tcl.nextLine();
		
//		guardarClientes(nombre);
//		mostrarClientes();
		List<Cliente> clientes = getAll();

		for (Cliente cliente : clientes) {
			System.out.println(cliente.getNombre());
		}
		


	}
	
//	public static void guardarClientes(String nombre) {
//		
//		Cliente cliente = new Cliente();
//		cliente.setNombre(nombre);
//		
//		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();
//		
//		UnidadPersistencia.getInstance().getEntityManager().persist(cliente);
//		
//		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();
//	}
//	
//	public static void mostrarClientes() {
//		
//		System.out.println(UnidadPersistencia.getInstance().getEntityManager().createQuery("select nombre from cliente", Cliente.class).getResultList());
//	}
//	
//	public static List<Cliente> getAll(){
//        return UnidadPersistencia.getInstance().getEntityManager().createQuery("from Cliente order by nombre", Cliente.class).getResultList();
//    }

}
