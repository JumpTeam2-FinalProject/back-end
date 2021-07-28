package com.cognixia.jump.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.NewReview;
import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.model.User;
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
	
    public Review addReview(NewReview newReview) {
    	
    	Review review = newReview.getReview();
    	
    	Restaurant restaurantHolder = new Restaurant();
    	
    	restaurantHolder.setRestaurant_id(newReview.getRestaurant_id());
    	
    	User userHolder = new User();
    	
    	userHolder.setUserId(newReview.getUser_id());
    	
    	review.setRestaurant(restaurantHolder);
    	review.setUser(userHolder);
    	
    	repo.save(review);
    	
    	return review;
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
