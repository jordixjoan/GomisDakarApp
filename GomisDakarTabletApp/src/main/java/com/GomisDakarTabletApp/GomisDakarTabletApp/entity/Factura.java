package com.GomisDakarTabletApp.GomisDakarTabletApp.entity;

import java.io.Serializable;
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
	
	@Column
	private String km;
	
	@Column
	private int combustible;
	
	@Column
	private java.sql.Date fechaEntrada;
	
	@Column
	private int autorizacion;
	
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

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
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
		return "Facturas [id=" + id + ", km=" + km + ", servicios=" + servicios + ", subtotal=" + subtotal + ", total="
				+ total + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, km, servicios, subtotal, total);
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
		return Objects.equals(id, other.id) && Objects.equals(km, other.km)
				&& Objects.equals(servicios, other.servicios) && Objects.equals(subtotal, other.subtotal)
				&& Objects.equals(total, other.total);
	}
	
}
