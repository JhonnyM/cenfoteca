package com.cenfotec.cenfoteca.services;

import java.util.List;

import com.cenfotec.cenfoteca.contracts.UsuarioHasAlquilerRequest;
import com.cenfotec.cenfoteca.pojo.UsuarioHasAlquilerPOJO;

public interface UsuarioHasAlquilerServiceInterface {

	List<UsuarioHasAlquilerPOJO> findByAlquiler(UsuarioHasAlquilerRequest ur);
	List<UsuarioHasAlquilerPOJO> findByUsuario(UsuarioHasAlquilerRequest ur);
	List<UsuarioHasAlquilerPOJO> getAll();
	boolean save(UsuarioHasAlquilerRequest ur);
	boolean delete(UsuarioHasAlquilerRequest ur);
	
}
