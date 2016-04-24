package com.cenfotec.cenfoteca.contracts;

import com.cenfotec.cenfoteca.pojo.TipoUsuarioPOJO;

public class TipoUsuarioRequest extends BaseRequest{
	
	private TipoUsuarioPOJO tipo;

	public TipoUsuarioRequest() {
		super();
	}
	public TipoUsuarioPOJO getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuarioPOJO tipo) {
		this.tipo = tipo;
	}
	
	
	
}
