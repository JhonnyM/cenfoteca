package com.cenfotec.cenfoteca.controllers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cenfotec.cenfoteca.contracts.RentResponse;
import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.services.RentServiceInterface;
import com.cenfotec.cenfoteca.services.TipoAlquilerServiceInterface;
import com.cenfotec.cenfoteca.utils.Utils;
import com.cenfotec.cenfoteca.contracts.RentRequest;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/rent")
public class RentController {
	
	@Autowired private ServletContext servletContext;
	@Autowired private TipoAlquilerServiceInterface tipoAlquilerService;
	@Autowired private RentServiceInterface rentService;
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public RentResponse create(
			@RequestParam("file") MultipartFile file,
			@RequestParam("idTipoAlquiler") int idTipoAlquiler,
			@RequestParam("name") String name,
			@RequestParam("description") String description){	
		
		RentResponse rs = new RentResponse();
		String resultFileName = Utils.writeToFile(file,servletContext);
		if(!resultFileName.equals("")){
			
			Alquiler alquiler = new Alquiler();
			alquiler.setName(name);
			alquiler.setDescription(description);
			alquiler.setImage(resultFileName);
			alquiler.setTipoAlquiler(tipoAlquilerService.getTipoAlquilerById(idTipoAlquiler));
			
			Boolean state = rentService.saveRent(alquiler);
			
			if(state){
				rs.setCode(200);
				rs.setCodeMessage("rent created succesfully");
			}
			
		}else{
			//create a common webservice error codes enum or statics
			rs.setCode(409);
			rs.setErrorMessage("create/edit conflict");
		}
	
		return rs;		
	}

	@RequestMapping(value ="/getAll", method = RequestMethod.GET)
	public RentResponse getAll(){
		
		RentResponse rs = new RentResponse();
		rs.setAlquilerList(rentService.getAll());	
		return rs;
	}

	@RequestMapping(value ="/delete", method = RequestMethod.POST)
	public RentResponse delete(@RequestBody RentRequest ur){	
			
		RentResponse ut = new RentResponse();
		if(rentService.delete(ur)) {
			ut.setCode(200);
			ut.setCodeMessage("Alquiler borrado");
		}
		return ut;		
	}


	// @RequestMapping(value = "/save", method = RequestMethod.POST)
	// public RentResponse save(@RequestBody RentRequest request) {
	
	// 	RentResponse response = new RentResponse();
	// 	boolean saved = rentService.save(request);
		
	// 	if(saved){
	// 		response.setCode(200);
	// 		response.setCodeMessage("El evento ha sido guardado exitosamente");
	// 	}
	// 	else
	// 	{
	// 		response.setCode(404);
	// 		response.setCodeMessage("Hubo un error al momento de guardar el evento");
	// 	}
	// 	return response;
	// }


	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public RentResponse save(
			@RequestParam("idAlquiler") int idAlquiler,
			@RequestParam("file") MultipartFile file,
			@RequestParam("idTipoAlquiler") int idTipoAlquiler,
			@RequestParam("name") String name,
			@RequestParam("description") String description){	
		
		RentResponse rs = new RentResponse();
		String resultFileName = Utils.writeToFile(file,servletContext);
		if(!resultFileName.equals("")){
			
			Alquiler alquiler = rentService.findByIdAlquiler(idAlquiler);
			alquiler.setName(name);
			alquiler.setDescription(description);
			alquiler.setImage(resultFileName);
			alquiler.setTipoAlquiler(tipoAlquilerService.getTipoAlquilerById(idTipoAlquiler));
			
			Boolean state = rentService.saveRent(alquiler);
			
			if(state){
				rs.setCode(200);
				rs.setCodeMessage("rent created succesfully");
			}
			
		}else{
			//create a common webservice error codes enum or statics
			rs.setCode(409);
			rs.setErrorMessage("create/edit conflict");
		}
	
		return rs;		
	}



	
}
