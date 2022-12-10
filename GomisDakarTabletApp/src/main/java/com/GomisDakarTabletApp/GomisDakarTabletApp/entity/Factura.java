package com.GomisDakarTabletApp.GomisDakarTabletApp.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Factura implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	private Long id;
	
	@Column(columnDefinition="default 2022000")
	private int idFactura;
	
	@Column
	private String estado;
	
	@Column
	private int km;
	
	@Column
	private String combustible;

	@Column
	private LocalDate fechaEntrada;
	
	@Column
	private int numeroLlave;
	
	@Column
	private boolean autorizacion;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "factura_servicios",
			   joinColumns=@JoinColumn(name="factura_id"),
			   inverseJoinColumns=@JoinColumn(name="servicio_id"))
	private Set<Servicio> servicios;
	
	@Column
	private String subtotal;
	
	@Column
	private String total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public boolean getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(boolean autorizacion) {
		this.autorizacion = autorizacion;
	}

	public Set<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", idFactura=" + idFactura + ", estado=" + estado + ", km=" + km + ", combustible="
				+ combustible + ", fechaEntrada=" + fechaEntrada + ", autorizacion=" + autorizacion + ", servicios="
				+ servicios + ", subtotal=" + subtotal + ", total=" + total + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(autorizacion, combustible, estado, fechaEntrada, id, idFactura, km, servicios, subtotal,
				total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		return autorizacion == other.autorizacion && Objects.equals(combustible, other.combustible)
				&& Objects.equals(estado, other.estado) && Objects.equals(fechaEntrada, other.fechaEntrada)
				&& Objects.equals(id, other.id) && idFactura == other.idFactura && km == other.km
				&& Objects.equals(servicios, other.servicios) && Objects.equals(subtotal, other.subtotal)
				&& Objects.equals(total, other.total);
	}
	
}
