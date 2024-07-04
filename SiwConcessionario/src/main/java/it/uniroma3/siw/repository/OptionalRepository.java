package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Optional;

public interface OptionalRepository extends CrudRepository<Optional,Long> {
	
	boolean existsByName(String name);
	
	@Query("SELECT o FROM Optional o WHERE LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	public List<Optional> findOptionals(String name);
	

}
