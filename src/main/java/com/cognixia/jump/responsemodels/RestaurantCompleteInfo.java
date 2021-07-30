package com.cognixia.jump.responsemodels;

import java.util.List;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.util.DataFormatters;

public class RestaurantCompleteInfo {
	
	private Integer restaurant_id;
	private String text;
	private String address;
	private String description;
	private String cuisine;
	private List <ReviewDetails> reviews;
	
	public RestaurantCompleteInfo() {};
	
	public RestaurantCompleteInfo(Restaurant restaurant) {
		
		if(restaurant != null) {
			restaurant_id = restaurant.getRestaurant_id();
			text = restaurant.getText();
			address = restaurant.getAddress();
			description = restaurant.getDescription();
			cuisine = restaurant.getCuisine();
			reviews = DataFormatters.getDetailsFromReviews(restaurant.getReviews());
		}
		
	}

	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public String getText() {
		return text;
	}

	public String getAddress() {
		return address;
	}

	public String getDescription() {
		return description;
	}

	public String getCuisine() {
		return cuisine;
	}

	public List<ReviewDetails> getReviews() {
		return reviews;
	}

	@Override
	public String toString() {
		return "RestaurantCompleteInfo [restaurant_id=" + restaurant_id + ", text=" + text + ", address=" + address
				+ ", description=" + description + ", cuisine=" + cuisine + ", reviews=" + reviews + "]";
	}
	
	

}
