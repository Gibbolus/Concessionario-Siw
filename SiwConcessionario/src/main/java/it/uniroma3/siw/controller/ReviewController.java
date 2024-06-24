package it.uniroma3.siw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.validator.ReviewValidator;
import jakarta.validation.Valid;

@Controller
public class ReviewController{
	
	@Autowired ReviewService reviewService;
	
	@Autowired ReviewValidator reviewValidator;
	
	
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
	
	@PostMapping(value = "/rev")
	public String newReview(@Valid @ModelAttribute("review") Review review, BindingResult reviewBindingResult) {
		this.reviewValidator.validate(review, reviewBindingResult);
		if(!reviewBindingResult.hasErrors()) {
			this.reviewService.save(review);
			return "redirect: review/" + review.getId();
		}
		else
			return "supplier/formNewReview.html";
	}
	
	
	
}
