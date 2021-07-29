package com.cognixia.jump.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.model.ReviewDetails;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository repo;
	
	public List<ReviewDetails> getReviews() {
		
		List<ReviewDetails> reviews = new ArrayList<ReviewDetails>();
		
		List<Review> reviewsList = repo.findAll();
		
		for (Review review : reviewsList) {
			reviews.add(new ReviewDetails(review, review.getUser().getUserId(), review.getRestaurant().getRestaurant_id()));
		}
		
		return reviews;
		
	}	
	
	public ReviewDetails getReviewsById(int review_id) {
		
		Optional<Review> reviewOpt = repo.findById(review_id);
		
		ReviewDetails review = new ReviewDetails(reviewOpt.get(), reviewOpt.get().getUser().getUserId(), reviewOpt.get().getRestaurant().getRestaurant_id());
		
		if (reviewOpt.isPresent()) {
			return review;
		}
		
		else {
			return review;
		}
	}
	
    public Review addReview(ReviewDetails newReview) {
    	
    	
    	Review review = newReview.getReview();
    	
    	Restaurant restaurantHolder = new Restaurant();
    	
    	restaurantHolder.setRestaurant_id(newReview.getRestaurant_id());
    	
    	User userHolder = new User();
    	
    	userHolder.setUserId(newReview.getUser_id());
    	
    	review.setRestaurant(restaurantHolder);
    	review.setUser(userHolder);
    	
    	LocalDate localDate = LocalDate.now();
    	
    	Date date = java.sql.Date.valueOf(localDate);
    	
    	review.setDate(date);
    	
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
