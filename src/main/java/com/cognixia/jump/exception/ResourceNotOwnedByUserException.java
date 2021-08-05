package com.cognixia.jump.exception;

public class ResourceNotOwnedByUserException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotOwnedByUserException(String message) {
		super(message);
	}

}
