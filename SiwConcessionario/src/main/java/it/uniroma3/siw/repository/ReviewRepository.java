package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Car;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.Supplier;

public interface ReviewRepository extends CrudRepository<Review,Long> {
	
	boolean existsBySupplierAndCar(Supplier s, Car c);

}
