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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	private Long id;
	
	@Column
	@NotBlank(message="No puede estar en blanco.")
	@Size(min=9,max=9,message="Escribe un DNI válido.")
	private String dni;
	
	@Column
	@NotBlank(message="No puede estar en blanco.")
	private String nombre;
	
	@Column
	@NotBlank(message="No puede estar en blanco.")
	private String direccion;
	
	@Column
	@NotBlank(message="No puede estar en blanco.")
	private String ciudad;
	
	@Column
	@NotBlank(message="No puede estar en blanco.")
	@Size(min=9,max=9,message="Escribe un teléfono válido.")
	private String telefono;
	
	@Column
	@Size(min=5,max=5,message="Escribe un código postal válido.")
	private String cp;
	
	@Column
	@NotBlank(message="No puede estar en blanco.")
	private String email;
	
	@Column
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			   joinColumns=@JoinColumn(name="user_id"),
			   inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_motos",
			   joinColumns=@JoinColumn(name="user_id"),
			   inverseJoinColumns=@JoinColumn(name="moto_id"))
	private Set<Moto> motos;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_facturas",
			   joinColumns=@JoinColumn(name="user_id"),
			   inverseJoinColumns=@JoinColumn(name="factura_id"))
	private Set<Factura> facturas;
	
	public User() {
		super();
	}

	public User(Long id) {
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Moto> getMotos() {
		return motos;
	}

	public void setMotos(Set<Moto> motos) {
		this.motos = motos;
	}

	public Set<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad="
				+ ciudad + ", telefono=" + telefono + ", cp=" + cp + ", email=" + email
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", roles=" + roles + ", motos="
				+ motos + ", facturas=" + facturas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ciudad, confirmPassword, cp, direccion, dni, email, facturas, id, motos, nombre, password,
				roles, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(confirmPassword, other.confirmPassword)
				&& Objects.equals(cp, other.cp) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(dni, other.dni) && Objects.equals(email, other.email)
				&& Objects.equals(facturas, other.facturas) && Objects.equals(id, other.id)
				&& Objects.equals(motos, other.motos) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(roles, other.roles);
	}
}
