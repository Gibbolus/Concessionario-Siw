package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Supplier;
import it.uniroma3.siw.repository.SupplierRepository;


@Service
public class SupplierService {
	
	@Autowired 
	private SupplierRepository supplierRepository;
	
	public Supplier findById( Long id) {
		return supplierRepository.findById(id).get();
	}
	
	public Iterable<Supplier> findAll(){
		return supplierRepository.findAll();
	}
	
	public void save(Supplier s) {
		supplierRepository.save(s);
	}
	

	public List<Supplier> findBySurname(String surname) {
		return supplierRepository.findBySurname(surname);
	}
	
	public void remove(Supplier s) {
		supplierRepository.delete(s);
	}

	public boolean existsByNameSurnameBirth(Supplier s) {
		return supplierRepository.existsByName(s.getName()) && supplierRepository.existsBySurname(s.getSurname()) && 
				supplierRepository.existsByBirth(s.getBirth());
	}
	
	
}
