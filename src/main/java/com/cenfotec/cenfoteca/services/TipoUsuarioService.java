package com.cenfotec.cenfoteca.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenfotec.cenfoteca.contracts.TipoUsuarioRequest;
import com.cenfotec.cenfoteca.pojo.TipoUsuarioPOJO;
import com.cenfotec.cenfoteca.repositories.TipoUsuarioRepository;
import com.cenfotec.cenfoteca.utils.Utils;
import com.cenfotec.cenfoteca.ejb.TipoUsuario;;

@Service
public class TipoUsuarioService implements TipoUsuarioServiceInterface {

	@Autowired
	private TipoUsuarioRepository repo;
	
	@Override
	@Transactional
	public List<TipoUsuarioPOJO> getAll() {
		return repo.findAll()
				   .parallelStream()
				   .map(t -> Utils.copyProperties(t, TipoUsuarioPOJO::new))
				   .collect(Collectors.toList());
	}

	@Override
	public Optional<TipoUsuarioPOJO> getTipoUsuarioById(int idTipoAlquiler) {
		return Optional.ofNullable(repo.findOne(idTipoAlquiler))
				       .map(p -> Utils.copyProperties(p, TipoUsuarioPOJO::new));
		
	}
	@Override
	public boolean saveTipoUsuario(TipoUsuarioRequest r) {
		TipoUsuario tu = repo.save(Utils.copyProperties(r.getTipo(), TipoUsuario::new));
		return tu != null;
	}

	@Override
	public boolean deleteTipoUsuario(TipoUsuarioRequest r) {
		
		int id = r.getTipo().getIdTipoUsuario();
		boolean exists = repo.exists(id);
		if(exists)
			repo.delete(r.getTipo().getIdTipoUsuario());
		return exists;
	}

	


	
	

}
