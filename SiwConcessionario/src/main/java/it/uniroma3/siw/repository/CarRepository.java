package it.uniroma3.siw.repository;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Car;


public interface CarRepository extends CrudRepository<Car,Long> {
	
	public Car findByModello(String modello);

}
