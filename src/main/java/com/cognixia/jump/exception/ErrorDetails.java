package com.cognixia.jump.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String details;
	private List<String> messages; // optional for when there are multiple messages. (front end can always just use "message" if no "messages")

	public ErrorDetails(Date timestamp, String message, String details) {
		this(timestamp, message, details, new ArrayList<String>());
	}
	public ErrorDetails(Date timestamp, String message, String details, List<String> messages) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.messages = messages;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
	public List<String> getMessages(){
		return messages;
	}
	
	public void addMessage(String newMessage) {
		messages.add(newMessage);
	}

}
