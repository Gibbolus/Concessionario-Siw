package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Optional;
import it.uniroma3.siw.repository.OptionalRepository;

@Service
public class OptionalService {

	@Autowired OptionalRepository optionalRepository;
	
	public Iterable<Optional> findAll() {
		return this.optionalRepository.findAll();
	}

	public void save(Optional optional) {
		this.optionalRepository.save(optional);
	}
	
	public Optional findById(Long id) {
		return optionalRepository.findById(id).get();
	}

	public boolean existsByName(String name) {
		return optionalRepository.existsByName(name);
	}
	
}
