package com.cognixia.jump.responsemodels;

// models object to send in response object.
	// doesn't represent a database table
public class AuthenticationResponse {

	private final String jwt;
	private final UserCompleteInfo user;

	public AuthenticationResponse(String jwt, UserCompleteInfo user) {
		this.jwt = jwt;
		this.user = user;
	}

	public String getJwt() {
		return jwt;
	}
	
	public UserCompleteInfo getUser() {
		return user;
	}

}
