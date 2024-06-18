package it.uniroma3.siw.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Car;
import it.uniroma3.siw.model.Supplier;

@Component
public class CarValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Car.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Car c= (Car) target;
		Supplier s= c.getSupplier();
		for( Car car: s.getCars()) {
			if(c.getModello().equals(car.getModello())) {
				errors.reject("car.duplicate");
			}
		}
		
		
	}

}
