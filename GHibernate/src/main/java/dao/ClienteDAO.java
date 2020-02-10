package dao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

import controller.UnidadPersistencia;
import model.Cliente;

public class ClienteDAO {
	
	public static void InsertarCliente (String nombre, String password){
		
		Cliente cliente = new Cliente ();
		cliente.setNombre(nombre);
		cliente.setContrasenya(password);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();
		
		UnidadPersistencia.getInstance().getEntityManager().persist(cliente);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();

	}
	
//	public static void devolverCliente () {
//		
//		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();
//		
//		UnidadPersistencia.getInstance().getEntityManager().createQuery("from Cliente order by Id", Cliente.class);
//		
//		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();
//	}
	
	public static List<Cliente> getClientes() {
		List<Cliente> clientes = UnidadPersistencia.getInstance().getEntityManager().createQuery("from Cliente order by Id", Cliente.class).getResultList();
		return clientes;
	}
	
	public static void show() {
		
		DefaultTableModel model = new DefaultTableModel();
		String[] arrayClientes;

		List<Cliente> clientes = UnidadPersistencia.getInstance().getEntityManager().createQuery("from Cliente order by Id", Cliente.class).getResultList();
		
		for (Cliente cliente : clientes) {
			String[] datosCliente = new String[] {
					cliente.getId().toString(), cliente.getNombre()
			};
			model.addRow(datosCliente);
			
//			String id ="";
//			String nombre="";
//			id = cliente.getId().toString();
//			nombre = cliente.getNombre();
//			String stringcliente = id + " " + nombre;
//			System.out.println(stringcliente);
		}
					
	}
	
	public static void getUser(String name, String password) {
		Cliente user = UnidadPersistencia.getInstance().getEntityManager().createQuery(
				"SELECT nombre FROM Cliente c WHERE c.nombre LIKE :" + name, Cliente.class);
	}

}

/*
 * import controller.EntityManagerHelper;
import model.Article;
import model.Client;

import java.util.List;

public class ClientDAO extends PersistenceDAO<Client> {

    @Override
    public List<Client> getAll() {
        return EntityManagerHelper.getInstance().getEntityManager().createQuery("from Client order by Id", Client.class).getResultList();
    }

    @Override
    public Client findById(long id) {
        return EntityManagerHelper.getInstance().getEntityManager().find(Client.class, id);
    }
}
 */