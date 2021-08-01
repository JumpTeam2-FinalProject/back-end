package com.cognixia.jump.responsemodels;

import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.model.User;
import com.cognixia.jump.util.DataFormatters;

public class UserCompleteInfo {
	
	private String username;
	private String firstName;
	private String lastName;
	private List<ReviewDetails> reviews;
	
	public UserCompleteInfo(User user) {
		if (user == null) {
			this.username = null;
			this.firstName = "NOT_FOUND";
			this.lastName = null;
			this.reviews = new ArrayList<ReviewDetails>();
		} else {
			this.username = user.getUsername();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.reviews = DataFormatters.getDetailsFromReviews(user.getReviews());
		}
	}
	
	public UserCompleteInfo(String username, String firstName, String lastName, List<ReviewDetails> reviews) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.reviews = reviews;
	}
	
	public String getUsername() {
		return username;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public List<ReviewDetails> getReviews() {
		return reviews;
	}

	@Override
	public String toString() {
		return "UserCompleteInfo [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", reviewsCount=" + reviews.size() + "]";
	}
	
}
