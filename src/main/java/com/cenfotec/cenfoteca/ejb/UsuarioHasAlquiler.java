package com.cenfotec.cenfoteca.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Alquiler database table.
 * 
 */
@Entity
@NamedQuery(name = "UsuarioHasAlquiler.findAll", query = "SELECT a FROM UsuarioHasAlquiler a")
public class UsuarioHasAlquiler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAlquilerXUsuario;

	//bi-directional many-to-one association to Evento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_idUsuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Evento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="alquiler_idAlquiler")
	private Alquiler alquiler;

	private boolean estado_renta;

	public UsuarioHasAlquiler() {
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Alquiler getAlquiler() {
		return alquiler;
	}

	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}

	public boolean isEstado_renta() {
		return estado_renta;
	}

	public void setEstado_renta(boolean estado_renta) {
		this.estado_renta = estado_renta;
	}
	
	public int getId() {
		return this.idAlquilerXUsuario;
	}


}