package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Car;
import it.uniroma3.siw.model.OptionalCar;


import it.uniroma3.siw.service.CarService;
import it.uniroma3.siw.service.OptionalCarService;



@Controller
public class OptionalCarController {
	
	@Autowired OptionalCarService optCarService;
	
	@Autowired CarService carService;
	
	
	@GetMapping(value = "admin/removeOptional/{idCar}/{idOptional}")
	public String AdminremoveOptional(@PathVariable("idCar")Long idCar,@PathVariable("idOptional")Long idOpt, Model model) {
		Car car=this.carService.findById(idCar);
		OptionalCar optCar=this.optCarService.findById(idOpt);
		car.getOptionals().remove(optCar);
		this.optCarService.remove(optCar);
		this.carService.save(car);
		model.addAttribute("car", car);
		return "redirect:/admin/manageOptionals/" + car.getId();
	}
	
	@GetMapping(value = "supplier/removeOptional/{idCar}/{idOptional}")
	public String removeOptional(@PathVariable("idCar")Long idCar,@PathVariable("idOptional")Long idOpt, Model model) {
		Car car=this.carService.findById(idCar);
		OptionalCar optCar=this.optCarService.findById(idOpt);
		car.getOptionals().remove(optCar);
		this.optCarService.remove(optCar);
		this.carService.save(car);
		model.addAttribute("car", car);
		return "redirect:/supplier/manageOptionals/" + car.getId();
	}
	
}
