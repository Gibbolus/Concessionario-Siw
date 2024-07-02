package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.ReviewService;

@Component
public class ReviewValidator implements Validator {

	@Autowired
	ReviewService reviewService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Review.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Review r = (Review) target;
		if (r.getVoto() < 0 || r.getVoto() > 10) {
			errors.reject("voto.error");
		}
		for (Review review : r.car.getReviews()) { // sfoglia tutte le recensioni
			if (review.getSupplier().equals(r.getSupplier())) { // se tra le recensioni ne trovi una con lo stesso
																// fornitore
				errors.reject("recensione.duplicate"); // il fornitore ha già rilasciato una recensione per quell'auto
			}
		}
	}

}
