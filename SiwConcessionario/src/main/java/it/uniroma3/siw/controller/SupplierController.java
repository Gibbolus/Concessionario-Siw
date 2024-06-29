package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Supplier;
import it.uniroma3.siw.service.SupplierService;
import it.uniroma3.siw.validator.SupplierValidator;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@Controller
public class SupplierController {

	@Autowired
	SupplierService supplierService;

	@Autowired
	SupplierValidator supplierValidator;

	@Autowired
	EntityManager entityManager;

	@GetMapping(value = "/supplier/{id}")
	public String getSupplier(@PathVariable("id") Long id, Model model) {
		model.addAttribute("supplier", this.supplierService.findById(id));
		return "supplier.html";
	}

	@GetMapping(value = "/supplier")
	public String showSuppliers(Model model) {
		model.addAttribute("suppliers", this.supplierService.findAll());
		return "suppliers.html";
	}

	@GetMapping(value = "/formSearchSupplier")
	public String formSearchSupplier() {
		return "formSearchSupplier.html";
	}

	@PostMapping(value = "/formSearchSupplier")
	public String getSupplier(@RequestParam String name, Model model) {
		String query = "SELECT s FROM Supplier s WHERE LOWER(s.name) LIKE LOWER('%" + name + "%')";
		List<Supplier> suppliers = this.entityManager.createQuery(query, Supplier.class).getResultList();
		model.addAttribute("suppliers", suppliers);
		return "suppliers.html";
	}

	@GetMapping(value = "/admin/formNewSupplier")
	public String formNewSupplier(Model model) {
		Supplier supplier = new Supplier();
		model.addAttribute("supplier", supplier);
		return "admin/formNewSupplier.html";
	}

	@PostMapping(value = "/sup")
	public String newSupplier(@Valid @ModelAttribute("supplier") Supplier supplier,
			BindingResult supplierBindingResult) {
		this.supplierValidator.validate(supplier, supplierBindingResult);
		if (!supplierBindingResult.hasErrors()) {
			this.supplierService.save(supplier);
			return "redirect:supplier/" + supplier.getId();
		}
		else {
			return "admin/formNewSupplier.html";
		}
	}

	@GetMapping(value = "/admin/manageSuppliers")
	public String manageSuppliers(Model model) {
		model.addAttribute("suppliers", this.supplierService.findAll());
		return "admin/manageSuppliers.html";
	}

	@GetMapping(value = "/admin/removeSupplier/{id}")
	public String removeSupplier(@PathVariable("id") Long id) {
		Supplier s = this.supplierService.findById(id);
		this.supplierService.remove(s);
		return "redirect:/admin/manageSuppliers";
	}

}
