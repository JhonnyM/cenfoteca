package com.cenfotec.cenfoteca.contracts;

import com.cenfotec.cenfoteca.pojo.UsuarioHasAlquilerPOJO;

public class UsuarioHasAlquilerRequest extends BaseRequest {
	
	private UsuarioHasAlquilerPOJO usuarioHasAlquiler;
	
	public UsuarioHasAlquilerRequest() {
		super();
	}
	
	public UsuarioHasAlquilerPOJO getUsuarioHasAlquiler() {
		return usuarioHasAlquiler;
	}
	
	public void setUsuarioHasAlquiler(UsuarioHasAlquilerPOJO usuarioHasAlquiler) {
		this.usuarioHasAlquiler = usuarioHasAlquiler;
	}

	@Override
	public String toString() {
		return "UsuarioHasAlquiler [usuarioHasAlquiler=" + usuarioHasAlquiler + "]";
	}
}
