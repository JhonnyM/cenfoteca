package com.cenfotec.cenfoteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.UsuarioHasAlquilerRequest;
import com.cenfotec.cenfoteca.contracts.UsuarioHasAlquilerResponse;
import com.cenfotec.cenfoteca.services.UsuarioHasAlquilerServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/userRent")
public class UsuarioHasAlquilerController {
	
	@Autowired private UsuarioHasAlquilerServiceInterface relation;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.GET)
	public UsuarioHasAlquilerResponse getAll(){	
			
		UsuarioHasAlquilerResponse us = new UsuarioHasAlquilerResponse();
		us.setCode(200);
		us.setCodeMessage("items rented by users fetch success");
		us.setUsuarioHasAlquiler(relation.getAll());
		return us;		
	}
	
	@RequestMapping(value ="/rentItem", method = RequestMethod.POST)
	public UsuarioHasAlquilerResponse rentItem(@RequestBody UsuarioHasAlquilerRequest ur){	
		
		UsuarioHasAlquilerResponse us = new UsuarioHasAlquilerResponse();
		Boolean state = relation.save(ur);
	
		if(state){
			us.setCode(200);
			us.setCodeMessage("user rented item succesfully");
		}
		return us;
		
	}
	
	@RequestMapping(value ="/returnItem", method = RequestMethod.POST)
	public UsuarioHasAlquilerResponse returnItem(@RequestBody UsuarioHasAlquilerRequest ur){	
		
		UsuarioHasAlquilerResponse us = new UsuarioHasAlquilerResponse();
		Boolean state = relation.delete(ur);
	
		if(state){
			us.setCode(200);
			us.setCodeMessage("item returned succesfully");
		}
		return us;
		
	}

}
