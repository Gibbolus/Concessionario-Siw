package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.OptionalCar;
import it.uniroma3.siw.repository.OptionalCarRepository;


@Service
public class OptionalCarService {
	
	@Autowired OptionalCarRepository optionalCarRepository;

	public Iterable<OptionalCar> findAll() {
		return this.optionalCarRepository.findAll();
	}

	public void save(OptionalCar optCar) {
		this.optionalCarRepository.save(optCar);
	}
	
	public OptionalCar findById(Long id) {
		return optionalCarRepository.findById(id).get();
	}
	
	public void remove(OptionalCar optCar) {
		this.optionalCarRepository.delete(optCar);
	}
}
