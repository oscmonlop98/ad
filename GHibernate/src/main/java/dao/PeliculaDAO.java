package dao;

import java.util.List;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

import controller.UnidadPersistencia;
import model.Cliente;
import model.Pelicula;

public class PeliculaDAO {

	public static void InsertarPelicula (Pelicula pelicula){
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();
		
		UnidadPersistencia.getInstance().getEntityManager().persist(pelicula);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();
		
	}
	
	public static List<Pelicula> getPeliculas() {
		List<Pelicula> peliculas = UnidadPersistencia.getInstance().getEntityManager().createQuery("from Pelicula order by Id", Pelicula.class).getResultList();
		return peliculas;
	}
	
	public static void eliminarPelicula (Pelicula pelicula){
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();
		
		UnidadPersistencia.getInstance().getEntityManager().remove(pelicula);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();
		System.out.println("ELIMINADO");

	}
	
	public static void show() {
		
		DefaultTableModel model = new DefaultTableModel();
		String[] arrayClientes;

		List<Pelicula> peliculas = UnidadPersistencia.getInstance().getEntityManager().createQuery("from Pelicula order by Id", Pelicula.class).getResultList();
		
		for (Pelicula pelicula : peliculas) {
			String[] datosPelicula = new String[] {
					pelicula.getId().toString(), pelicula.getTitulo()
			};
			model.addRow(datosPelicula);
			
		}
					
	}
}
