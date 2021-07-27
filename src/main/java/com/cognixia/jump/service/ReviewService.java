package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository repo;
	
	public List<Review> getReviews() {
		
		return repo.findAll();
		
	}	
	
	public Review getReviewsById(int review_id) {
		
		Optional<Review> reviewOpt = repo.findById(review_id);
	
		
		if (reviewOpt.isPresent()) {
			return reviewOpt.get();
		}
		
		else {
			return reviewOpt.get();
		}
	}
	
	public Review deleteReviewById(int review_id) {
		
	    Optional<Review> reviewOpt = repo.findById(review_id);
	    
	    repo.deleteById(review_id);
	    
	    if (reviewOpt.isPresent()) {
	    	return reviewOpt.get();
	    }

		else {
			return reviewOpt.get();
		}

	}
	
}
