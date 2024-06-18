package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Car;
import it.uniroma3.siw.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public Car findById(Long id) {
		return carRepository.findById(id).get();
	}
	
	public Iterable<Car> findAll(){
		return carRepository.findAll();
	}
	
	public void save(Car c) {
		carRepository.save(c);
	}
	
	public void delete(Car car) {
		carRepository.delete(car);
	}

	public Car findByModello(String modello) {
		return carRepository.findByModello(modello);
	}
	
	

}
