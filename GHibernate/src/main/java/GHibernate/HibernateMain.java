package GHibernate;


import java.util.Scanner;

import models.Cliente;

public class HibernateMain {

	public static void main(String[] args) {
		String nombre = "";
		Scanner tcl = new Scanner(System.in);
		
		System.out.println("Introduce el nombre del cliente: ");
		nombre = tcl.nextLine();
		
		guardarClientes(nombre);
		


	}
	
	public static void guardarClientes(String nombre) {
		
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		
		UnidadPersistencia.getInstance().getInstance().getEntityManager().getTransaction().begin();
		
		UnidadPersistencia.getInstance().getEntityManager().persist(cliente);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();
	}

}
