package com.cognixia.jump.exception;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> badCredentials(Exception ex, WebRequest request) {
		
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleFailedValidationError(ConstraintViolationException ex, WebRequest request) {
		
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDetails);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericError(Exception ex, WebRequest request) {
		
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
	}
	
	//Exception Handling for when account is created with same username or Restaurant with same name
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity <?> handleResourceAlreadyExistsError(Exception ex, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
		
	}
	
	@ExceptionHandler(ResourceDoesNotExistException.class)
	public ResponseEntity<?> handleResourceDoesNotExistError(Exception ex, WebRequest request){
		
ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
		
		
	}
}
