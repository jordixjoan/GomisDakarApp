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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Moto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	private Long id;
	
	@Column
	@NotBlank(message="No puede estar en blanco.")
	private String motocicleta;
	
	@Column
	@NotBlank(message="No puede estar en blanco.")
	private String modelo;
	
	@Column
	@NotBlank(message="No puede estar en blanco.")
	@Size(min=7,max=7,message="Escribe una matricula válida.")
	private String matricula;
	
	@Column
	@NotBlank(message="No puede estar en blanco.")
	@Size(min=17,max=17,message="Escribe un número de xásis válido.")
	private String xasis;
	
	public String getXasis() {
		return xasis;
	}

	public void setXasis(String xasis) {
		this.xasis = xasis;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "moto_factura",
			   joinColumns=@JoinColumn(name="moto_id"),
			   inverseJoinColumns=@JoinColumn(name="factura_id"))
	private Set<Factura> facturas;

	public Set<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}

	public Moto() {
		super();
	}

	public Moto(Long id) {
		super();
		this.id = id;
	}
	
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotocicleta() {
		return motocicleta;
	}

	public void setMotocicleta(String motocicleta) {
		this.motocicleta = motocicleta;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Moto [id=" + id + ", motocicleta=" + motocicleta + ", modelo=" + modelo + ", matricula=" + matricula
				+ ", xasis=" + xasis + ", facturas=" + facturas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(facturas, id, matricula, modelo, motocicleta, xasis);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moto other = (Moto) obj;
		return Objects.equals(facturas, other.facturas) && Objects.equals(id, other.id)
				&& Objects.equals(matricula, other.matricula) && Objects.equals(modelo, other.modelo)
				&& Objects.equals(motocicleta, other.motocicleta) && Objects.equals(xasis, other.xasis);
	}
	
}
