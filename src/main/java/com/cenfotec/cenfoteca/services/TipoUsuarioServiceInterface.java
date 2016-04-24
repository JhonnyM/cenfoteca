package com.cenfotec.cenfoteca.services;

import java.util.List;
import java.util.Optional;

import com.cenfotec.cenfoteca.contracts.TipoUsuarioRequest;
import com.cenfotec.cenfoteca.pojo.TipoUsuarioPOJO;

public interface TipoUsuarioServiceInterface {
	List<TipoUsuarioPOJO> getAll();
	Optional<TipoUsuarioPOJO> getTipoUsuarioById(int idTipoAlquiler);
	
	boolean saveTipoUsuario(TipoUsuarioRequest r);
	boolean deleteTipoUsuario(TipoUsuarioRequest r);
}
