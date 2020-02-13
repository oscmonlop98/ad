package dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import controller.UnidadPersistencia;
import model.Cliente;
import model.Pedido;
import model.PedidoLinea;
import model.Pelicula;

public class PedidoDAO {

	public static void InsertarPedido(Pedido pedido, Cliente user) {
		
		Long a = user.getId();
		
		Cliente cliente = UnidadPersistencia.getInstance().getEntityManager().find(Cliente.class, a);
		System.out.println(cliente);
		pedido.setCliente(cliente);

//		PedidoLinea pedidoLinea2 = new PedidoLinea(pedido);
//		Articulo articulo2 = entityManager.find(Articulo.class, 2L);
//		pedidoLinea2.setArticulo(articulo2);
//		pedidoLinea2.setUnidades(new BigDecimal(6));

		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();

		UnidadPersistencia.getInstance().getEntityManager().persist(pedido);

		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();

		// System.out.printf("%3d %s %s %s %n", pedido.getId(), pedido.getFecha(),
		// pedido.getCliente().getnombre());
	}
	
	public static List<Pedido> getPedidos() {
		List<Pedido> pedidos = UnidadPersistencia.getInstance().getEntityManager().createQuery("from Pedido order by Id", Pedido.class).getResultList();
		return pedidos;
	}
	
	public static void eliminarPedido (Pedido pedido){
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();
		
		UnidadPersistencia.getInstance().getEntityManager().remove(pedido);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();
		System.out.println("ELIMINADO");

	}

	public static void insert() {
		Cliente cliente = UnidadPersistencia.getInstance().getEntityManager().find(Cliente.class, 2L);
		Pedido pedido = new Pedido(cliente);
		PedidoLinea pedidoLinea1 = new PedidoLinea(pedido);
		Pelicula pelicula = UnidadPersistencia.getInstance().getEntityManager().find(Pelicula.class, 1L);
		pedidoLinea1.setArticulo(pelicula);
		pedidoLinea1.setUnidades(new BigDecimal(4));
		PedidoLinea pedidoLinea2 = new PedidoLinea(pedido);
		Pelicula pelicula2 = UnidadPersistencia.getInstance().getEntityManager().find(Pelicula.class, 2L);
		pedidoLinea2.setArticulo(pelicula2);
		pedidoLinea2.setUnidades(new BigDecimal(5));
		UnidadPersistencia.getInstance().getEntityManager().persist(pedido);
	}

}
