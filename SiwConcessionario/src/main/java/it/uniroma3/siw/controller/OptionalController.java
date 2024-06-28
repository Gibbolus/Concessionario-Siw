package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Car;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Optional;
import it.uniroma3.siw.model.OptionalCar;
import it.uniroma3.siw.service.CarService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.OptionalCarService;
import it.uniroma3.siw.service.OptionalService;
import it.uniroma3.siw.validator.OptionalValidator;
import jakarta.validation.Valid;

@Controller
public class OptionalController {
	
	@Autowired OptionalService optionalService;
	
	@Autowired CarService carService;
	
	@Autowired OptionalCarService optCarService;
	
	@Autowired OptionalValidator optionalValidator;
	
	@Autowired GlobalController gc;
	
	@Autowired CredentialsService credentialsService;
	
	
	@GetMapping(value = "/admin/formNewOptional")
	public String AdminformNewOptional(Model model) {
		Optional optional=new Optional();
		model.addAttribute("optional",optional);
		
		return "admin/formNewOptional.html";
	}
	
	@GetMapping(value = "/supplier/formNewOptional")
	public String formNewOptional(Model model) {
		Optional opt=new Optional();
		model.addAttribute("optional",opt);
		
		return "supplier/formNewOptional.html";
	}
	
	@PostMapping(value = "/optional")
	public String newOptional(@Valid @ModelAttribute("optional")Optional opt,
            BindingResult ingredientBindingResult) {
		this.optionalValidator.validate(opt,ingredientBindingResult);
		
		if(!ingredientBindingResult.hasErrors()) {
		this.optionalService.save(opt);
		return "redirect:/optional";
		}
		else {
			UserDetails u=gc.getUser();
			String username=u.getUsername();
			Credentials credenziali=this.credentialsService.getCredentials(username);
			if(credenziali.getRole().equals("SUPPLIER"))
				return "/supplier/formNewOptional.html";
			else 
				return "/admin/formNewOptional.html";
		}
	}
	
	@GetMapping(value = "/optional")
	  public String showOptionals(Model model) {
	     model.addAttribute("optionals", this.optionalService.findAll());
	    return "optionals.html";
	  }
	
	@GetMapping(value = "admin/manageOptionals/{id}")
	public String AdminmanageOptionals(@PathVariable("id") Long id, Model model) {
		Car car=this.carService.findById(id);
		model.addAttribute("car",car);
		List<Optional> optionalNonPresenti= new ArrayList<Optional>();
		for(Optional opt : this.optionalService.findAll()) {
			boolean presente=false;
			for(OptionalCar optCar : car.getOptionals()) {
				if(optCar.getOpt().equals(opt)) {
					presente=true;
				}
			}
			if(!presente) {
				optionalNonPresenti.add(opt);
			}
		}
		model.addAttribute("optionals",optionalNonPresenti );
		return "admin/manageOptionals.html";
	}
	
	@GetMapping(value = "supplier/manageOptionals/{id}")
	public String manageOptionals(@PathVariable("id") Long id, Model model) {
		Car car=this.carService.findById(id);
		model.addAttribute("car",car);
		List<Optional> optionalNonPresenti= new ArrayList<Optional>();
		for(Optional opt : this.optionalService.findAll()) {
			boolean presente=false;
			for(OptionalCar optCar : car.getOptionals()) {
				if(optCar.getOpt().equals(opt)) {
					presente=true;
				}
			}
			if(!presente) {
				optionalNonPresenti.add(opt);
			}
		}
		model.addAttribute("optionals", optionalNonPresenti);
		return "supplier/manageOptionals.html";
	}
	
	
	@GetMapping(value = "admin/setOptionalToCar/{idCar}/{idOptional}")
	public String AdminsetOptionalToCar(Model model,@PathVariable("idCar") Long idCar, @PathVariable("idOptional") Long idOpt) {
		Optional opt= this.optionalService.findById(idOpt);
		Car car= this.carService.findById(idCar);
		
		OptionalCar optCar=new OptionalCar();
		optCar.setOpt(opt);
		optCar.setCar(car);
		this.optCarService.save(optCar);
		car.getOptionals().add(optCar);
		this.carService.save(car);
		model.addAttribute("car",car);
		return "redirect:/admin/formUpdateCar/" + car.getId();
	}
	
	@GetMapping(value = "supplier/setOptionalToCar/{idCar}/{idOptional}")
	public String setOptionalToCar(Model model,@PathVariable("idCar") Long idCar, @PathVariable("idOptional") Long idOpt) {
		Optional opt= this.optionalService.findById(idOpt);
		Car car= this.carService.findById(idCar);
		
		OptionalCar optCar=new OptionalCar();
		optCar.setOpt(opt);
		optCar.setCar(car);
		this.optCarService.save(optCar);
		car.getOptionals().add(optCar);
		this.carService.save(car);
		model.addAttribute("car",car);
		return "redirect:/supplier/manageOptionals/" + car.getId();
	}
}
