package com.cenfotec.cenfoteca.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cenfotec.cenfoteca.contracts.UsersRequest;
import com.cenfotec.cenfoteca.ejb.Usuario;
import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;
import com.cenfotec.cenfoteca.pojo.UsuarioPOJO;
import com.cenfotec.cenfoteca.repositories.UsersRepository;
import com.cenfotec.cenfoteca.utils.Utils;

@Service
public class UsersService implements UsersServiceInterface{

	@Autowired private UsersRepository usersRepository;
	
	@Override
	@Transactional
	public List<UsuarioPOJO> getAll(UsersRequest ur) {
		List<Usuario> users =  usersRepository.findAll();
		return generateUserDtos(users);
	}
	
	@Override
	@Transactional
	public List<UsuarioPOJO> getAllByName(UsersRequest ur) {
		List<Usuario> users =  usersRepository.findByFirstnameContaining(ur.getSearchTerm());
		return generateUserDtos(users);
	}
	
	private List<UsuarioPOJO> generateUserDtos(List<Usuario> users){
            return users.stream()
                     .map(u -> Utils.copyProperties(u, UsuarioPOJO::new))
                     .peek(dto -> dto.setPassword(""))
                     .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public boolean saveUser(UsersRequest ur) {
	
		Usuario user = new Usuario();
		BeanUtils.copyProperties(ur.getUser(), user);
		user.setPassword("set md5 password"); 
		
		Usuario nuser = usersRepository.save(user);
		
		return nuser != null;
		
	}

	@Override
	@Transactional
	public List<UsuarioPOJO> getAlquileres(UsersRequest ur) {
		
		/*
		Usuario user = usersRepository.findOne(ur.getUser().getIdUsuario());
		List<UsuarioPOJO> dtos = new ArrayList<UsuarioPOJO>();
		UsuarioPOJO dto = new UsuarioPOJO();

		//initial properties
		BeanUtils.copyProperties(user,dto);
		
		List<AlquilerPOJO> alquileres = new ArrayList<AlquilerPOJO>();
		user.getAlquilers().stream().forEach(alq -> {
			AlquilerPOJO alqDto = new AlquilerPOJO();
			BeanUtils.copyProperties(alq,alqDto);
			alquileres.add(alqDto);
		});
		
		dto.setAlquileres(alquileres);
		dtos.add(dto);
		return dtos;
		*/
		
		Usuario user = usersRepository.findOne(ur.getUser().getIdUsuario());
		
		UsuarioPOJO dtoU = user.getAlquilers()
				               .stream()
				               .map(alq -> Utils.copyProperties(alq, AlquilerPOJO::new))
				               .collect(Collectors.collectingAndThen(Collectors.toList(), 
				                        p -> Utils.copyProperties(user, new UsuarioPOJO(p))));
		return Arrays.asList(dtoU);
	}
}