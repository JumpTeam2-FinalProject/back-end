package com.cognixia.jump.responsemodels;

import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.util.DataFormatters;

public class UserCompleteInfo {
	
	//public enum Role { USER, ADMIN }
	
	private String username;
	private String firstName;
	private String lastName;
	private List<ReviewDetails> reviews;
	private Role role;
	
	
	public UserCompleteInfo(User user) {
		if (user == null) {
			this.username = null;
			this.firstName = "NOT_FOUND";
			this.lastName = null;
			this.reviews = new ArrayList<ReviewDetails>();
			this.role = null;
		} else {
			this.username = user.getUsername();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.reviews = DataFormatters.getDetailsFromReviews(user.getReviews());
			this.role = user.getRole();
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
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserCompleteInfo [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", reviewsCount=" + reviews.size() + "]";
	}
	
}
