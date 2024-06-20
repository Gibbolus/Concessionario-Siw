package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Optional;
import it.uniroma3.siw.service.OptionalService;

@Component
public class OptionalValidator implements Validator {
	
	@Autowired OptionalService optionalService;

	
	@Override
	public boolean supports(Class<?> aClass) {
		return Credentials.class.equals(aClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Optional opt=(Optional) target;
		if(this.optionalService.existsByName(opt.getName())) {
			errors.reject("optional.duplicate");
		}
		
	}


}
