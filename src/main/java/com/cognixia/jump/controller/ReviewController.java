package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.ReviewRepository;

@RequestMapping("/api")
@RestController
public class ReviewController {

	@Autowired
	ReviewRepository repo;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Review>> getReviews() {
		
		return ResponseEntity.status(200)
				 .body(repo.findAll());
	}	
	
	@GetMapping("/reviews/{review_id}")
	public ResponseEntity<Review> getReviewsById(@Valid @PathVariable("review_id") int review_id) {
		
		Optional<Review> reviewOpt = repo.findById(review_id);
	
		
		if (reviewOpt.isPresent()) {
			return ResponseEntity.status(200)
					 .body(reviewOpt.get());
		}
		
		else {
			return ResponseEntity.status(400)
					 .body(reviewOpt.get());
		}
	}
	
	@DeleteMapping("/reviews/{review_id}")
	public ResponseEntity<Review> deleteTodoById(@Valid @PathVariable("review_id") int review_id) {
		
	    Optional<Review> reviewOpt = repo.findById(review_id);
	    
	    repo.deleteById(review_id);
	    
	    if (reviewOpt.isPresent()) {
	    	return ResponseEntity.status(200)
					 .body(reviewOpt.get());
	    }

		else {
			return ResponseEntity.status(400)
					 .body(reviewOpt.get());
		}

	}
	
}
