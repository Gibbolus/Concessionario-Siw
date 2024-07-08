package it.uniroma3.siw.controller;


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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Car;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.Supplier;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CarService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.validator.ReviewValidator;
import jakarta.validation.Valid;

@Controller
public class ReviewController{
	
	@Autowired ReviewService reviewService;
	
	@Autowired ReviewValidator reviewValidator;
	
	@Autowired CredentialsService credentialsService;
	
	@Autowired CarService carService;
	
	@Autowired GlobalController gc;
	
	
	@GetMapping(value = "/review/{id}")
	public String getReview(@PathVariable("id") Long id, Model model) {
		model.addAttribute("review", this.reviewService.findById(id));
		return "review.html";
	}
	
	@GetMapping(value = "/car/{carId}/reviews")
	public String showAllReviews(@PathVariable("carId") Long carId, Model model) {
		Car c = this.carService.findById(carId);
		/*int numeroRecensioni=c.getReviews().size();
		int totale=0;
		for(Review r: c.getReviews()) {
			totale+=r.getVoto();
		}
		float media=0;
		if(numeroRecensioni!=0) {
			media=(float)totale/numeroRecensioni;
		}
		model.addAttribute("media", media);
		*/
		model.addAttribute("reviews", c.getReviews());
		model.addAttribute("car", c);
		return "reviews.html";
	}
	
	@GetMapping(value = "/formNewReview/{carId}")
	public String formNewReview(@PathVariable("carId") Long carId, Model model) {
		Review review = new Review();
		Car c = carService.findById(carId);
		model.addAttribute("car", c);
		model.addAttribute("review", review);
		
		UserDetails u = gc.getUser();
		String username = u.getUsername();
		Credentials credentials = this.credentialsService.getCredentials(username);
		if(credentials.getRole().equals("ADMIN")) {
			return "/admin/formNewReview.html"; 
		}
		return "/supplier/formNewReview.html";
	}
	
	@PostMapping(value = "/review")
	public String newReview(@Valid @ModelAttribute("review") Review review, @RequestParam("carId") Long carId, BindingResult reviewBindingResult) {
		UserDetails u = gc.getUser();
		String username = u.getUsername();
		Credentials credentials = this.credentialsService.getCredentials(username);
		User currentUser = credentials.getUser();
		Supplier supplier = currentUser.getSupplier();
		Car reviewdCar = this.carService.findById(carId);
		review.setCar(reviewdCar);
		review.setSupplier(supplier);
		
		this.reviewValidator.validate(review, reviewBindingResult);
		if(!reviewBindingResult.hasErrors()) {
			supplier.getReviews().add(review);
			reviewdCar.getReviews().add(review);
			this.reviewService.save(review);
			this.carService.save(reviewdCar);
			return "redirect:review/" + review.getId();
		}
		else
			return "redirect:/formNewReview/" + reviewdCar.getId();
	}
	
	@GetMapping(value="/manageReviews")
	public String manageReviews(Model model) {
		UserDetails u = gc.getUser();
		String username = u.getUsername();
		Credentials credentials = this.credentialsService.getCredentials(username);
		User currentUser = credentials.getUser();
		
		if(credentials.getRole().equals("ADMIN")) { //se si tratta di un admin passa tutte le review presenti sul sistema
			model.addAttribute("reviews", this.reviewService.findAll());
			return "admin/manageReviews.html";
		}
		else { //si tratta di un fornitore
			Supplier supplier = currentUser.getSupplier();
			model.addAttribute("reviews",supplier.getReviews()); //passa solo le review del fornitore corrente
		
			return "supplier/manageReviews.html";
		}
	}
	
	
	@GetMapping(value = "/removeReview/{id}")
	public String removeReview(@PathVariable("id") Long id) {
		Review review = this.reviewService.findById(id);
		review.getSupplier().getReviews().remove(review);
		review.setSupplier(null);
		Car reviewdCar = review.getCar();
		List<Review> carReviews = reviewdCar.getReviews();
		
		carReviews.remove(review);
		
		this.reviewService.remove(review);
		return "redirect:/manageReviews";
	}
	
	
	
	
}
