package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.service.SupplierService;

@Controller
public class SupplierController {
	
	@Autowired SupplierService supplierService;
	
	
	@GetMapping("/supplier/{id}")
	public String getSupplier(@PathVariable("id") Long id,Model model) {
		model.addAttribute("supplier", this.supplierService.findById(id));
		return "supplier.html";
	}
	@GetMapping("/supplier")
	public String showSuppliers(Model model) {
		model.addAttribute("suppliers", this.supplierService.findAll());
		return "suppliers.html";
	}
	
	@GetMapping("/formSearchSupplier")
	public String formSearchSupplier() {
		return "formSearchSupplier.html";
	}
	@PostMapping("/formSearchSupplier")
	public String getSupplier(String surname, Model model) {
		model.addAttribute("suppliers", this.supplierService.findBySurname(surname));
		return "suppliers.html";
	}
	

}
