package com.cognixia.jump.util;

import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.responsemodels.ReviewDetails;
import com.cognixia.jump.service.RestaurantCompleteInfo;

public class DataFormatters {
	
	public static List<ReviewDetails> getDetailsFromReviews(List<Review> reviews) {
		
		List<ReviewDetails> reviewsDetails = new ArrayList<ReviewDetails>();
		
		for (Review review : reviews) {
			reviewsDetails.add(new ReviewDetails(review));
		}
		
		return reviewsDetails;
	}
	
}
