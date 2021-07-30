package com.cognixia.jump.responsemodels;

import java.util.Date;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.model.User;

public class ReviewDetails {
	
	private Review review;
	private UserBasicInfo user;
	private RestaurantBasicInfo restaurant;
	
	public ReviewDetails(Review review, User user, Restaurant restaurant) {
		super();
		this.review = review;
		this.user = new UserBasicInfo(user);
		this.restaurant = new RestaurantBasicInfo(restaurant);
	}
	
	public ReviewDetails(Review review) {
		this(review, review.getUser(), review.getRestaurant());
	}
	
	public ReviewDetails() {
		this.review = null;
		this.user = null;
		this.restaurant = null;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public UserBasicInfo getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = new UserBasicInfo(user);
	}

	public RestaurantBasicInfo getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		setRestaurant(new RestaurantBasicInfo(restaurant));
	}
	
	public void setRestaurant(RestaurantBasicInfo restaurant) {
		this.restaurant = restaurant;
	}
	
}
