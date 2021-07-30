package com.cognixia.jump.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Restaurant {
	
	@Id
	@Column(name = "RESTAURANT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer restaurant_id;
	
	@Column(name = "TEXT")
	private String text;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToMany(mappedBy="restaurant" , cascade = CascadeType.ALL)
	@JsonManagedReference(value = "restaurant-review")
	private List <Review> reviews;
	
	public Restaurant() {}

	public Restaurant(Integer restaurant_id, String text, String address, String description, List<Review> reviews) {
		super();
		this.restaurant_id = restaurant_id;
		this.text = text;
		this.address = address;
		this.description = description;
		this.reviews = reviews;
	}

	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	

}