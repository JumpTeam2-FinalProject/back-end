package com.cognixia.jump.util;

import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.responsemodels.ReviewDetails;
import com.cognixia.jump.responsemodels.RestaurantCompleteInfo;

public class DataFormatters {
	
	public static List<ReviewDetails> getDetailsFromReviews(List<Review> reviews) {
		
		List<ReviewDetails> reviewsDetails = new ArrayList<ReviewDetails>();
		
		for (Review review : reviews) {
			reviewsDetails.add(new ReviewDetails(review));
		}
		
		return reviewsDetails;
	}
	
	public static List<RestaurantCompleteInfo> getDetailsFromRestaurants(List<Restaurant> restaurants){
		
		List<RestaurantCompleteInfo> restaurantCompleteInfo = new ArrayList<RestaurantCompleteInfo>();
		
		for(Restaurant restaurant: restaurants) {
			restaurantCompleteInfo.add(new RestaurantCompleteInfo(restaurant));
		}
		
		return restaurantCompleteInfo;
		
	}
	
}
