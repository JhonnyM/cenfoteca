package com.cenfotec.cenfoteca.ejb;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "TipoUsuario.findAll", query = "SELECT t FROM TipoUsuario t")
public class TipoUsuario implements Serializable{


	private static final long serialVersionUID = 8312297880270892265L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTipoUsuario;
	private String name;
	
	
	@OneToMany(mappedBy = "tipo")
	private Set<Usuario> usuarios;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	
	public boolean addUsuario(Usuario u) {
		u.setTipo(this);
		return usuarios.add(u);
	}
	
	public boolean removeUsuario(Usuario u) {
		boolean removed = usuarios.remove(u);
		if(removed) 
			u.setTipo(null);
		return removed;
	}	
	
	@Override
	public String toString() {
		
		return String.format(
				"Id: %s, Name: %s", 
				idTipoUsuario, name);	
	}
	
	
	
	

}
