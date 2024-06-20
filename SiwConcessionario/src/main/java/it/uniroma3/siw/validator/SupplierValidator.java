package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Supplier;

import it.uniroma3.siw.service.SupplierService;

@Component
public class SupplierValidator implements Validator{
	
	@Autowired SupplierService supplierService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Credentials.class.equals(aClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
			Supplier s=(Supplier) target;
			
			if(s.getName()!=null && s.getSurname()!=null && s.getBirth()!=null 
					&& supplierService.existsByNameSurnameBirth(s)) {
				errors.reject("supplier.duplicate");
			}
			if(s.getBirth().getYear()<1900 || s.getBirth().getYear()>2024) {
				errors.reject("birth.error");
			}
		
		
		
		}
}
