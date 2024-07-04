package it.uniroma3.siw.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Car;


public interface CarRepository extends CrudRepository<Car,Long> {
	
	public Car findByModello(String modello);
	
	
	@Query ("SELECT c FROM Car c WHERE LOWER(c.marca) LIKE LOWER(CONCAT('%', :marca, '%'))")
	public List<Car> findCars(String marca);
	

}
