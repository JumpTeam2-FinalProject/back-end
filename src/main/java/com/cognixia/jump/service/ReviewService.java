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
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ReviewRepository;
import com.cognixia.jump.responsemodels.ReviewDetails;
import com.cognixia.jump.util.DataFormatters;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository repo;
	
	public List<ReviewDetails> getReviews() {
		
		List<Review> reviewsList = repo.findAll();
		
		return DataFormatters.getDetailsFromReviews(reviewsList);
		
	}	
	
	public ReviewDetails getReviewsById(int review_id) {
		
		Optional<Review> reviewOpt = repo.findById(review_id);
		
		return new ReviewDetails(reviewOpt.get());
	}
	
    public ReviewDetails addReview(Review newReview) {
/***********
 * NOTE:
 * 		I (David) rewrote some of this method to reduce amount of code and make it work with my updates elsewhere.
 * 		I left the old code here commented out for now just in case it's needed.
 * ***********/ 
    	
//    	Review review = newReview.getReview();
//    	Restaurant restaurantHolder = new Restaurant();
//    	restaurantHolder.setRestaurant_id(newReview.getRestaurant_id());
//    	User userHolder = new User();
//    	userHolder.setUserId(newReview.getUser_id());
//    	review.setRestaurant(restaurantHolder);
//    	review.setUser(userHolder);
    	
    	LocalDate localDate = LocalDate.now();
    	
    	Date date = java.sql.Date.valueOf(localDate);
    	
    	newReview.setDate(date);
    	
    	repo.save(newReview);
    	
    	return new ReviewDetails(newReview);
    }
	
	public ReviewDetails deleteReviewById(int review_id) {
		
	    Optional<Review> reviewOpt = repo.findById(review_id);
	    
    	repo.deleteById(review_id);

    	return new ReviewDetails(reviewOpt.get());
	}
	
}
