package com.cenfotec.cenfoteca.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The persistent class for the Usuario database table.
 * 
 */
@Entity
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUsuario;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	@ManyToMany
	@JoinTable(
		name = "UsuarioHasAlquiler", 
		joinColumns = {
			@JoinColumn(name = "Usuario_idUsuario")
		}, 
		inverseJoinColumns = {
			@JoinColumn(name = "Alquiler_idAlquiler")
		}
	)
	
	private List<Alquiler> alquilers;
	
	@ManyToOne
	private TipoUsuario tipo;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Alquiler> getAlquilers() {
		return this.alquilers;
	}

	public void setAlquilers(List<Alquiler> alquilers) {
		this.alquilers = alquilers;
	}
	
	@Override
	public boolean equals(Object o) {
	
		if(!(o instanceof Usuario))
			return false;
			
		Usuario u = (Usuario)o;	
		return u.getIdUsuario() == u.getIdUsuario();	
	}
	
	@Override
	public int hashCode() {   
		return 31 * 17 + idUsuario;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		String alquileres = alquilers.stream()
				.map(Alquiler::toString)
				.collect(Collectors.joining(",\n"));

		return String.join("\n", 
				"Id: " + idUsuario,
				"Email: " + email,
				"First Name: " + firstname,
				"Last Name: " + lastname,
				"Tipo: " + tipo,
				"Alquleres: " + alquileres);
	}

}