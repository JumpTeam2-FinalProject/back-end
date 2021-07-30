package com.cognixia.jump.responsemodels;

import com.cognixia.jump.model.Restaurant;

public class RestaurantBasicInfo {

	private Integer restaurant_id;
	private String name;
	private String address;
	private String description;
	private int reviewsCount;
	
	public RestaurantBasicInfo() {
		this(null);
	}
	public RestaurantBasicInfo(Restaurant restaurant) {
		super();
		if (restaurant == null) {
			this.restaurant_id = -1;
			this.name = null;
			this.address = null;
			this.description = null;
			this.reviewsCount = -1;
		} else {
			this.restaurant_id = restaurant.getRestaurant_id();
			this.name = restaurant.getName();
			this.address = restaurant.getAddress();
			this.description = restaurant.getDescription();
			this.reviewsCount = restaurant.getReviews().size();
		}
	}
	
	public Integer getRestaurant_id() {
		return restaurant_id;
	}
	public String getName() {
		return name;
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

}
