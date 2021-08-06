package com.cognixia.jump.controller;

import java.util.List;

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

import com.cognixia.jump.exception.ResourceDoesNotExistException;
import com.cognixia.jump.exception.ResourceNotOwnedByUserException;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.ReviewRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.responsemodels.ReviewDetails;
import com.cognixia.jump.service.ReviewService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api")
@RestController
public class ReviewController {

	@Autowired
	ReviewRepository repo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ReviewService service;
	
	@ApiOperation(value= "Get All Reviews" , 
			notes= "Get all reviews with their corresponding informations", 
			response = Review.class)
	@GetMapping("/reviews")
	public ResponseEntity<List<ReviewDetails>> getReviews() {
		
		return ResponseEntity.status(200)
				 .body(service.getReviews());
	}	
	
	@ApiOperation(value= "Get All Reviews in Simple Format" , 
			notes= "Get all reviews with their corresponding informations in Simple Format", 
			response = Review.class)
	@GetMapping("/reviews_simple")
	public ResponseEntity<List<Review>> getReviewsSimple() {
		return ResponseEntity.status(200)
				 .body(service.getReviewsSimple());
	}
	
	@ApiOperation(value= "Get Review by ID" , 
			notes= "Provide Review Id and return that review", 
			response = Review.class)
	@GetMapping("/reviews/{review_id}")
	public ResponseEntity<ReviewDetails> getReviewsById(@Valid @PathVariable("review_id") int review_id) {
		
		return ResponseEntity.status(200)
				 .body(service.getReviewsById(review_id));
	}
	
	@ApiOperation(value= "Post Reviews" , 
			notes= "Post a review", 
			response = Review.class)
	@PostMapping("/reviews/{restaurant_id}")
	public ResponseEntity<ReviewDetails> addReview(@PathVariable Integer restaurant_id, @RequestBody Review review) {
		return ResponseEntity.status(200)
				 .body(service.addReview(review, restaurant_id));
	}
	
	@ApiOperation(value= "Delete a review by ID" , 
			notes= "Provide Review ID to delete a specific Review", 
			response = Review.class)
	@DeleteMapping("/reviews/{review_id}")
	public ResponseEntity<ReviewDetails> deleteReviewById(@Valid @PathVariable("review_id") int review_id) throws ResourceDoesNotExistException, ResourceNotOwnedByUserException {
		
		return ResponseEntity.status(200)
				 .body(service.deleteReviewById(review_id));

	}
	
}
