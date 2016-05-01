package com.cenfotec.cenfoteca.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cenfotec.cenfoteca.contracts.UsuarioHasAlquilerRequest;
import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.ejb.Usuario;
import com.cenfotec.cenfoteca.ejb.UsuarioHasAlquiler;
import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;
import com.cenfotec.cenfoteca.pojo.UsuarioHasAlquilerPOJO;
import com.cenfotec.cenfoteca.pojo.UsuarioPOJO;
import com.cenfotec.cenfoteca.repositories.RentRepository;
import com.cenfotec.cenfoteca.repositories.UsersRepository;
import com.cenfotec.cenfoteca.repositories.UsuarioHasAlquilerRepository;
import com.cenfotec.cenfoteca.utils.Utils;

@Service
public class UsuarioHasAlquilerService implements UsuarioHasAlquilerServiceInterface{

	@Autowired private UsuarioHasAlquilerRepository usuarioHasAlquilerRepo;
	@Autowired private RentRepository itemRepository;
	@Autowired private UsersRepository userRepository;
	
	@Override
	@Transactional
	public List<UsuarioHasAlquilerPOJO> getAll() {
		List<UsuarioHasAlquiler> usuarioHasAlquilers =  usuarioHasAlquilerRepo.findAll();
		return generateUsuarioHasAlquilerDtos(usuarioHasAlquilers);
	}
	
	private List<UsuarioHasAlquilerPOJO> generateUsuarioHasAlquilerDtos(List<UsuarioHasAlquiler> users){
		List<UsuarioHasAlquilerPOJO> userRent = new ArrayList<UsuarioHasAlquilerPOJO>();
		
		users.stream().forEach(u -> {
			UsuarioHasAlquilerPOJO dto = new UsuarioHasAlquilerPOJO();

			BeanUtils.copyProperties(u,dto);
			
			dto.setAlquiler(new AlquilerPOJO());
			dto.setUsuario(new UsuarioPOJO());
			dto.setIdUsuarioHasAlquiler(u.getId());
			dto.setIdUsuario(u.getUsuario().getIdUsuario());
			BeanUtils.copyProperties(u.getAlquiler(),dto.getAlquiler());
			BeanUtils.copyProperties(u.getUsuario(), dto.getUsuario());
			
			userRent.add(dto);
		});	
		return userRent;
	}

	@Override
	@Transactional
	public List<UsuarioHasAlquilerPOJO> findByAlquiler(UsuarioHasAlquilerRequest ur) {
		
		List<UsuarioHasAlquiler> relation = usuarioHasAlquilerRepo.findByAlquilerContaining(ur.getUsuarioHasAlquiler().getAlquiler());
		return generateUsuarioHasAlquilerDtos(relation);
	}

	@Override
	@Transactional
	public List<UsuarioHasAlquilerPOJO> findByUsuario(UsuarioHasAlquilerRequest ur) {
		
		List<UsuarioHasAlquiler> relation = usuarioHasAlquilerRepo.findByUsuarioContaining(ur.getUsuarioHasAlquiler().getUsuario());
			
		return generateUsuarioHasAlquilerDtos(relation);
	}


	@Override
	@Transactional
	public boolean save(UsuarioHasAlquilerRequest ut) {
		
		UsuarioHasAlquiler newUserRent = new UsuarioHasAlquiler();
		UsuarioHasAlquiler nuseR = new UsuarioHasAlquiler();
		
		Usuario u = userRepository.findOne(ut.getUsuarioHasAlquiler().getIdUsuario());
		Alquiler i = itemRepository.findOne(ut.getUsuarioHasAlquiler().getIdItem());
		
		newUserRent.setAlquiler(i);
		newUserRent.setUsuario(u);
			
		nuseR = usuarioHasAlquilerRepo.save(newUserRent);

		
		return (nuseR== null) ? false : true;
	}

	@Override
	public boolean delete(UsuarioHasAlquilerRequest ur) {
		
		int id = ur.getUsuarioHasAlquiler().getIdUsuarioHasAlquiler();
		boolean exists = usuarioHasAlquilerRepo.exists(id);
		if(exists)
			usuarioHasAlquilerRepo.delete(id);
		return exists;
	}
}