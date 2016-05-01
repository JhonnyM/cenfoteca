package com.cenfotec.cenfoteca.contracts;

import java.util.List;

import com.cenfotec.cenfoteca.pojo.UsuarioHasAlquilerPOJO;

public class UsuarioHasAlquilerResponse extends BaseResponse{
	
	private List<UsuarioHasAlquilerPOJO> usuarioHasAlquiler;

	public UsuarioHasAlquilerResponse() {
		super();
	}

	
	public List<UsuarioHasAlquilerPOJO> getUsuarioHasAlquiler() {
		return usuarioHasAlquiler;
	}

	public void setUsuarioHasAlquiler(List<UsuarioHasAlquilerPOJO> usuarioHasAlquiler) {
		this.usuarioHasAlquiler = usuarioHasAlquiler;
	}

}
