package dao;

import java.math.BigDecimal;
import java.util.Scanner;

import controller.UnidadPersistencia;
import model.Cliente;
import model.Pedido;
import model.PedidoLinea;
import model.Pelicula;

public class PedidoDAO {
	
public static void InsertarPedido (){
		
		Scanner tcl=new Scanner(System.in);
		System.out.println("Indica el Cliente");
		Long clienteid=tcl.nextLong();
		Cliente cliente = UnidadPersistencia.getInstance().getEntityManager().find(Cliente.class, clienteid);
		
		Pedido pedido = new Pedido(cliente);
		PedidoLinea pedidoLinea1 = new PedidoLinea(pedido);
		
		System.out.println("Indica el Articulo");
		Scanner tcl3 =new Scanner(System.in);
		Long categoriaid=tcl3.nextLong();
		
		Pelicula pelicula = UnidadPersistencia.getInstance().getEntityManager().find(Pelicula.class, categoriaid);
		pedidoLinea1.setArticulo(pelicula);
		pedidoLinea1.setUnidades(new BigDecimal(4));
		
		/*PedidoLinea pedidoLinea2 = new PedidoLinea(pedido);
		Articulo articulo2 = entityManager.find(Articulo.class, 2L);
		pedidoLinea2.setArticulo(articulo2);
		pedidoLinea2.setUnidades(new BigDecimal(6));*/

		UnidadPersistencia.getInstance().getEntityManager().getTransaction().begin();
		
		UnidadPersistencia.getInstance().getEntityManager().persist(pedido);
		
		UnidadPersistencia.getInstance().getEntityManager().getTransaction().commit();

		//System.out.printf("%3d %s %s %s %n", pedido.getId(), pedido.getFecha(), pedido.getCliente().getnombre());
	}

}
