package com.cognixia.jump.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceDoesNotExistException;
import com.cognixia.jump.exception.ResourceNotOwnedByUserException;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.RestaurantRepository;
import com.cognixia.jump.repository.ReviewRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.responsemodels.ReviewDetails;
import com.cognixia.jump.util.DataFormatters;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository repo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RestaurantRepository restaurantRepo;
	
	public List<Review> getReviewsSimple() {
		
		List<Review> reviewsList = repo.findAll();
		
		return reviewsList;
		
	}
	
	public List<ReviewDetails> getReviews() {
		
		List<Review> reviewsList = repo.findAll();
		
		return DataFormatters.getDetailsFromReviews(reviewsList);
		
	}	
	
	public ReviewDetails getReviewsById(int review_id) {
		
		Optional<Review> reviewOpt = repo.findById(review_id);
		
		return new ReviewDetails(reviewOpt.get());
	}
	
    public ReviewDetails addReview(Review newReview, Integer restaurant_id) {
    	
    	// Set user to the current user that's logged in even if the review object in the request gives a different user
    	newReview.setUser(userRepo.getById(MyUserDetailsService.getCurrentUserId()));
    	newReview.setRestaurant(restaurantRepo.getById(restaurant_id));
    	
    	LocalDate todayLocalDate = LocalDate.now();
    	Date todayDate = java.sql.Date.valueOf(todayLocalDate);
    	newReview.setDate(todayDate);
    	
    	repo.save(newReview);
    	
    	return new ReviewDetails(newReview);
    }
	
	public ReviewDetails deleteReviewById(Integer review_id) throws ResourceDoesNotExistException, ResourceNotOwnedByUserException {
		
	    Optional<Review> reviewOpt = repo.findById(review_id);
	    
	    // Check that a review w/ id was found AND that it belongs to the current user 
	    if (reviewOpt.isPresent()) {
	    	throw new ResourceDoesNotExistException(
	    			"There is no review with the id " + review_id + ".");
	    }
	    if (reviewOpt.get().getUser().getUserId() == MyUserDetailsService.getCurrentUserId()) {
	    	throw new ResourceNotOwnedByUserException(
	    			"You may not update this review because you are not the user who created it.");
	    }
	    
    	repo.deleteById(review_id);

    	return new ReviewDetails(reviewOpt.get());
	}
	
}
