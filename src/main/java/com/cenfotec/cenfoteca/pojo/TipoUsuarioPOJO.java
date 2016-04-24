package com.cenfotec.cenfoteca.pojo;

public class TipoUsuarioPOJO {

	private int idTipoUsuario;
	private String name;
	

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
	
	@Override
	public String toString() {	
		return String.format(
				"Id: %s, Name: %s", 
				idTipoUsuario, name);	
	}
}
