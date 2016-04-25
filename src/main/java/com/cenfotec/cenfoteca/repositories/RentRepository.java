package com.cenfotec.cenfoteca.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.ejb.TipoUsuario;

public interface RentRepository extends CrudRepository<Alquiler,Integer> {
	List<Alquiler> findAll();
}
