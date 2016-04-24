package com.cenfotec.cenfoteca.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.TipoAlquilerResponse;
import com.cenfotec.cenfoteca.contracts.TipoUsuarioRequest;
import com.cenfotec.cenfoteca.contracts.TipoUsuarioResponse;
import com.cenfotec.cenfoteca.contracts.UsersRequest;
import com.cenfotec.cenfoteca.contracts.UsersResponse;
import com.cenfotec.cenfoteca.services.TipoUsuarioServiceInterface;
import com.cenfotec.cenfoteca.services.UsersServiceInterface;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/users")
public class UsersController {
	
	
	//meter estos en uno solo service despues
	@Autowired 
	private UsersServiceInterface usersService;
	@Autowired
	private TipoUsuarioServiceInterface tipoUserService;
	@Autowired 
	private HttpServletRequest request;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public UsersResponse getAll(@RequestBody UsersRequest ur){	
			
		UsersResponse us = new UsersResponse();
		
		us.setCode(200);
		us.setCodeMessage("users fetch success");
		us.setUsuarios(usersService.getAll(ur));
		return us;		
	}
	
	@RequestMapping(value ="/getAllByName", method = RequestMethod.POST)
	public UsersResponse getAllByName(@RequestBody UsersRequest ur){	
			
		UsersResponse us = new UsersResponse();
		us.setCode(200);
		us.setCodeMessage("users fetch success");
		us.setUsuarios(usersService.getAllByName(ur));
		return us;		
	}
	
	@RequestMapping(value ="/getAlquileres", method = RequestMethod.POST)
	public UsersResponse getAlquileres(@RequestBody UsersRequest ur){	
			
		UsersResponse us = new UsersResponse();
		us.setCode(200);
		us.setCodeMessage("users fetch success");
		us.setUsuarios(usersService.getAlquileres(ur));
		return us;		
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public UsersResponse create(@RequestBody UsersRequest ur){	
		
		UsersResponse us = new UsersResponse();
		boolean state = usersService.saveUser(ur);
	
		if(state) {
			us.setCode(200);
			us.setCodeMessage("user created succesfully");
		}
		return us;
		
	}
	@RequestMapping(value ="/getTiposUsuario", method = RequestMethod.POST)
	public TipoUsuarioResponse getTiposUsuario(@RequestBody UsersRequest ur){	
		
		
		TipoUsuarioResponse tr = new TipoUsuarioResponse();
		tr.setCode(200);
		tr.setCodeMessage("users fetch success");
		tr.setTipoUsuariosList(tipoUserService.getAll());
		return tr;		
	}
	
	@RequestMapping(value ="/createTipo", method = RequestMethod.POST)
	public TipoUsuarioResponse createTipo(@RequestBody TipoUsuarioRequest ur){	
			
		TipoUsuarioResponse ut = new TipoUsuarioResponse();
		if(tipoUserService.saveTipoUsuario(ur)) {
			ut.setCode(200);
			ut.setCodeMessage("user type created succesfully");
			ut.setTipoUsuariosList(tipoUserService.getAll());
		}
		return ut;		
	}
	
	@RequestMapping(value ="/deleteTipo", method = RequestMethod.POST)
	public TipoUsuarioResponse deleteTipo(@RequestBody TipoUsuarioRequest ur){	
			
		TipoUsuarioResponse ut = new TipoUsuarioResponse();
		if(tipoUserService.deleteTipoUsuario(ur)) {
			ut.setCode(200);
			ut.setCodeMessage("user type created succesfully");
		}
		return ut;		
	}
	
	
}
