package dao;

import java.util.Scanner;

import controller.UnidadPersistencia;
import model.Cliente;
import model.Pelicula;

public class PeliculaDAO {

	public static void InsertarPelicula (){
		
		Pelicula pelicula = new Pelicula();
		
		System.out.println("Dime el nombre de la película:");
		Scanner tcl =new Scanner(System.in);
		String nombre=tcl.nextLine();
		
		pelicula.setTitulo(nombre);					
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();
		
		UnidadPersistencia.getInstance().getEntityManager().persist(pelicula);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();
		UnidadPersistencia.getInstance().getEntityManager().close();			
		UnidadPersistencia.getInstance().getEntityManagerFactory().close();
	}
}
