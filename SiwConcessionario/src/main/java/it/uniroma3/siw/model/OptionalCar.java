package it.uniroma3.siw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OptionalCar {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	public Optional opt;
	
	@ManyToOne
	Car car;
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car=car;
	
	}
	
	public Optional getOptional(){
		return opt;
	}
	
	public void setOptional(Optional optional) {
		this.opt=optional;
	}
	
}
