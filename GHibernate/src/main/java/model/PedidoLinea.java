package model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class PedidoLinea {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn (name="pedido")
	private Pedido pedido;
	@ManyToOne
	@JoinColumn (name="articulo")
	private Pelicula pelicula;
	private BigDecimal precio =BigDecimal.ZERO;
	private BigDecimal unidades=BigDecimal.ZERO;
	private BigDecimal importe=BigDecimal.ZERO;
	
	private PedidoLinea() {} //Hibernate necesita un ctor sin parámetros
	
	public PedidoLinea(Pedido pedido) {
		this.pedido = pedido;
		pedido.getPedidoLineas().add(this);
	}
		
	
	public Long getId() {
		return id;
	}
	
	public Pedido gertPedido() {
		return pedido;
	}
	public void setArticulo(Pelicula pelicula) {
		precio = pelicula.getPrecio();
		unidades=BigDecimal.ONE;
		this.pelicula = pelicula;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}
	
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	public BigDecimal getUnidades() {
		return unidades;
	}
	
	public void setUnidades(BigDecimal unidades) {
		this.unidades = unidades;
	}
	
	@PrePersist
	@PreUpdate
	private void preGetImporte() {
		importe = precio.multiply(unidades);
	}
	public BigDecimal getImporte() {
		preGetImporte();
		return importe;
	}
}
