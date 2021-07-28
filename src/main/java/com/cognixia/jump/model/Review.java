package com.cognixia.jump.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.cognixia.jump.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Review {
// * * NOTE: Restaurant stuff needs un-commented out when related files are added! * * * * * * * * * * * * * *

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer review_id;
	
	private Integer rating;
	
	private String review;
	
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
	@JsonBackReference(value = "restaurant-review")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonBackReference(value = "user-review")
	private User user;
	

	public Review(Integer review_id, Integer rating, String review, Date date, Restaurant restaurant,
			User user) {
		super();
		this.review_id = review_id;
		this.rating = rating;
		this.review = review;
		this.date = date;
		this.restaurant = restaurant;
		this.user = user;
	}
	
	public Review() {}

	public Integer getReview_id() {
		return review_id;
	}

	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Review [review_id=" + review_id + ", rating=" + rating + ", review=" + review + ", date=" + date + "]";
	}
	
}
