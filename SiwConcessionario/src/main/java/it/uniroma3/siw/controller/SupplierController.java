package it.uniroma3.siw.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Supplier;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.SupplierRepository;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.SupplierService;
import it.uniroma3.siw.validator.SupplierValidator;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@Controller
public class SupplierController {

	/* private static final String UPLOAD_DIR = "C:\\Users\\Gabriele\\git\\Concessionario\\SiwConcessionario\\src\\main\\resources\\static\\images"; */
	
	 private static final String UPLOAD_DIR= "C:\\Users\\39345\\Documents\\Concessionario-Siw\\SiwConcessionario\\src\\main\\resources\\static\\images"; 
	 
	@Autowired
	SupplierService supplierService;
	
	@Autowired 
	SupplierRepository supplierRepository;
	
	@Autowired GlobalController gc;
	
	@Autowired CredentialsService credentialsService;

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
		List<Supplier> suppliers = this.supplierRepository.findSuppliers(name);
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
			@RequestParam("immagine") MultipartFile file, BindingResult supplierBindingResult, Model model) {
		this.supplierValidator.validate(supplier, supplierBindingResult);
		if (!supplierBindingResult.hasErrors()) {
			try {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				Path path = Paths.get(UPLOAD_DIR + File.separator + fileName);
				Files.write(path, file.getBytes());
				supplier.setUrlImage(fileName);

				this.supplierService.save(supplier);
				model.addAttribute("supplier", supplier);

				return "redirect:supplier/" + supplier.getId();

			} catch (IOException e) {
				e.printStackTrace();
				return "admin/formNewSupplier.html.hmtl";
			}
		}
		return "admin/formNewSupplier.html";
	}

	@GetMapping(value = "/admin/manageSuppliers")
	public String manageSuppliers(Model model) {
		List<Supplier> suppliers=(List<Supplier>) this.supplierService.findAll();
		UserDetails u = gc.getUser();
		String username = u.getUsername();
		Credentials credenziali = this.credentialsService.getCredentials(username);
		User utenteCorrente = credenziali.getUser();
		Supplier fornitoreCorrente = utenteCorrente.getSupplier();
		suppliers.remove(fornitoreCorrente); //rimuovi il fornitore associato all'admin per evitare che possa rimuovere se stesso
		model.addAttribute("suppliers", suppliers);
		return "admin/manageSuppliers.html";
	}

	@GetMapping(value = "/admin/removeSupplier/{id}")
	public String removeSupplier(@PathVariable("id") Long id) {
		Supplier s = this.supplierService.findById(id);
		this.supplierService.remove(s);
		return "redirect:/admin/manageSuppliers";
	}

}
