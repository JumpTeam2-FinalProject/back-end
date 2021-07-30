package com.cognixia.jump.responsemodels;

import com.cognixia.jump.model.User;

public class UserBasicInfo {
	
	private String username;
	private String firstName;
	private String lastName;
	private int reviewsCount;
	
	public UserBasicInfo(User user) {
		if (user == null) {
			this.username = null;
			this.firstName = "NOT_FOUND";
			this.lastName = null;
			this.reviewsCount = -1;
		}
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.reviewsCount = user.getReviews().size();
	}
	
	public UserBasicInfo(String username, String firstName, String lastName, int reviewsCount) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.reviewsCount = reviewsCount;
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
	public int getReviewsCount() {
		return reviewsCount;
	}

	@Override
	public String toString() {
		return "UserBasicInfo [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", reviewsCount=" + reviewsCount + "]";
	}
	
	
}
