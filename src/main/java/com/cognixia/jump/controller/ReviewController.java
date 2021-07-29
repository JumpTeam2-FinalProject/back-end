package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.ReviewDetails;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.ReviewRepository;
import com.cognixia.jump.service.ReviewService;

@RequestMapping("/api")
@RestController
public class ReviewController {

	@Autowired
	ReviewRepository repo;
	
	@Autowired
	ReviewService service;
	
	@GetMapping("/reviews")
	public ResponseEntity<List<ReviewDetails>> getReviews() {
		
		return ResponseEntity.status(200)
				 .body(service.getReviews());
	}	
	
	@GetMapping("/reviews/{review_id}")
	public ResponseEntity<ReviewDetails> getReviewsById(@Valid @PathVariable("review_id") int review_id) {
		
		return ResponseEntity.status(200)
				 .body(service.getReviewsById(review_id));
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<Review> addReview(@Valid @RequestBody ReviewDetails review) {
		
		return ResponseEntity.status(200)
				 .body(service.addReview(review));
	}
	
	@DeleteMapping("/reviews/{review_id}")
	public ResponseEntity<Review> deleteTodoById(@Valid @PathVariable("review_id") int review_id) {
		
		return ResponseEntity.status(200)
				 .body(service.deleteReviewById(review_id));

	}
	
}
