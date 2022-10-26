package com.GomisDakarTabletApp.GomisDakarTabletApp.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Servicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	private Long id;
	
	@Column
	private String cantidad;
	
	@Column
	private String servicio;
	
	@Column
	private String precio;
	
	@Column
	private String totalServicio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getTotalServicio() {
		return totalServicio;
	}

	public void setTotalServicio(String totalServicio) {
		this.totalServicio = totalServicio;
	}

	@Override
	public String toString() {
		return "Servicio [id=" + id + ", cantidad=" + cantidad + ", servicio=" + servicio + ", precio=" + precio
				+ ", totalServicio=" + totalServicio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, id, precio, servicio, totalServicio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servicio other = (Servicio) obj;
		return Objects.equals(cantidad, other.cantidad) && Objects.equals(id, other.id)
				&& Objects.equals(precio, other.precio) && Objects.equals(servicio, other.servicio)
				&& Objects.equals(totalServicio, other.totalServicio);
	}

}
