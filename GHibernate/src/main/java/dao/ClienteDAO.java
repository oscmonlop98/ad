package dao;

import java.util.List;
import java.util.Scanner;

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