package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Optional;

public interface OptionalRepository extends CrudRepository<Optional,Long> {
	
	boolean existsByName(String name);
	

}
