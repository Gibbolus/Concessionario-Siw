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
	
	@GetMapping(value = "/review")
	public String showAllReviews(Model model) {
		model.addAttribute("review", this.reviewService.findAll());
		return "reviews.html";
	}
	
	@GetMapping(value = "/supplier/formNewReview")
	public String formNewReview(Model model) {
		Review review = new Review();
		model.addAttribute("review", review);
		return "supplier/formNewReview.html";
	}
	
	@PostMapping(value = "/review/{carId}")
	public String newReview(@Valid @ModelAttribute("review") Review review, @PathVariable("id") Long carId, BindingResult reviewBindingResult) {
		UserDetails u = gc.getUser();
		String username = u.getUsername();
		Credentials credentials = this.credentialsService.getCredentials(username);
		User currentUser = credentials.getUser();
		Supplier supplier = currentUser.getSupplier();
		Car reviewdCar = this.carService.findById(carId);

		this.reviewValidator.validate(review, reviewBindingResult);
		if(!reviewBindingResult.hasErrors()) {
			supplier.getReviews().add(review);
			reviewdCar.getReviews().add(review);
			review.setCar(reviewdCar);
			this.reviewService.save(review);
			return "redirect: review/" + review.getId();
		}
		else
			return "supplier/formNewReview.html";
	}
	
	
	@GetMapping(value = "/admin/removeReview/{id}")
	public String removeReview(@PathVariable("id") Long id) {
		Review review = this.reviewService.findById(id);
		review.getSupplier().getReviews().remove(review);
		review.setSupplier(null);
		Car reviewdCar = review.getCar();
		List<Review> carReviews = reviewdCar.getReviews();
		for(Review r : carReviews) {
			if(r.equals(review)) {
				reviewdCar.getReviews().remove(r);
			}
		}
		this.reviewService.remove(review);
		return "admin/car" + reviewdCar.getId();
	}
	
	
	
	
}
