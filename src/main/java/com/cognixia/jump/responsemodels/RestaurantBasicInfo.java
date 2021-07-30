package com.cognixia.jump.responsemodels;

import com.cognixia.jump.model.Restaurant;

public class RestaurantBasicInfo {

	private Integer restaurant_id;
	private String text;
	private String address;
	private String description;
	private int reviewsCount;
	private String cuisine;
	
	public RestaurantBasicInfo() {
		this(null);
	}
	public RestaurantBasicInfo(Restaurant restaurant) {
		super();
		if (restaurant == null) {
			this.restaurant_id = -1;
			this.text = null;
			this.address = null;
			this.description = null;
			this.reviewsCount = -1;
			this.cuisine = null;
		} else {
			this.restaurant_id = restaurant.getRestaurant_id();
			this.text = restaurant.getText();
			this.address = restaurant.getAddress();
			this.description = restaurant.getDescription();
			this.cuisine = restaurant.getCuisine();
			this.reviewsCount = restaurant.getReviews().size();
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
	public int getReviewsCount() {
		return reviewsCount;
	}
	public String getCuisine() {
		return cuisine;
	}

}
