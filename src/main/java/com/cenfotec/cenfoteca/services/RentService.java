package com.cenfotec.cenfoteca.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;
import com.cenfotec.cenfoteca.repositories.RentRepository;

@Service
public class RentService implements RentServiceInterface{

	@Autowired private RentRepository rentRepository;
	
	@Override
	public Boolean saveRent(Alquiler alquiler) {
		Alquiler nalquiler = rentRepository.save(alquiler);
		return nalquiler != null;
	}

	@Override
	@Transactional
	public List<AlquilerPOJO> getAll() {
		List<Alquiler> tipos = rentRepository.findAll();
		List<AlquilerPOJO> dtos = new ArrayList<AlquilerPOJO>();
		tipos.stream().forEach(ta ->{
			AlquilerPOJO dto = new AlquilerPOJO();
			BeanUtils.copyProperties(ta, dto);
			dtos.add(dto);
		});
		return dtos;
	}
}