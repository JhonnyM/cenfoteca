package com.cenfotec.cenfoteca.services;

import java.util.List;

import com.cenfotec.cenfoteca.contracts.UsersRequest;
import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;
import com.cenfotec.cenfoteca.pojo.UsuarioPOJO;
import com.cenfotec.cenfoteca.contracts.RentRequest;


public interface RentServiceInterface {
	Boolean saveRent(Alquiler alquiler);
	public List<AlquilerPOJO> getAll();
	boolean delete(RentRequest r);
}
