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
import com.cenfotec.cenfoteca.contracts.RentRequest;

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


	@Override
	public boolean delete(RentRequest r) {
		
		int id = r.getAlquiler().getIdAlquiler();
		boolean exists = rentRepository.exists(id);
		if(exists)
			rentRepository.delete(r.getAlquiler().getIdAlquiler());
		return exists;
	}

	@Override
	public Boolean save (Alquiler alquiler){
		Alquiler s = rentRepository.save(alquiler);
		return !(s == null);
		
	}
	
	public Alquiler findByIdAlquiler(Integer id){
		Alquiler alquiler = rentRepository.findOne(id);
		return alquiler;
		
	}
}
