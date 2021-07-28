package com.cognixia.jump.model;

public class NewReview {
	
	private Review review;
	private Integer user_id;
	private Integer restaurant_id;
	
	public NewReview(Review review, Integer user_id, Integer restaurant_id) {
		super();
		this.review = review;
		this.user_id = user_id;
		this.restaurant_id = restaurant_id;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	
	
}
