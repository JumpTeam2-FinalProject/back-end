package com.cognixia.jump.controller;

import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceAlreadyExistsException;
import com.cognixia.jump.exception.ResourceDoesNotExistException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.responsemodels.AuthenticationRequest;
import com.cognixia.jump.responsemodels.AuthenticationResponse;
import com.cognixia.jump.responsemodels.UserCompleteInfo;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.service.UserService;
import com.cognixia.jump.util.JwtUtil;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api")
@RestController
@Validated
public class UserController {

	@Autowired
	private UserRepository repo;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@ApiOperation(value = "Authenticate User Credentials", notes = "Provide username and password to authenticate ", response = (JwtUtil.class))
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUserRemember(@RequestBody AuthenticationRequest request) throws Exception {
		return doAuthentication(request, true);
	}

	@PostMapping("/authenticate/remember")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) throws Exception {
		return doAuthentication(request, false);
	}

	public ResponseEntity<?> doAuthentication(AuthenticationRequest request, boolean shouldExpire) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Username or password is incorrect.", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateTokens(userDetails, shouldExpire);
		UserCompleteInfo newUserInfo = userService.getUserByUsername(userDetails.getUsername());
		return ResponseEntity.ok(new AuthenticationResponse(jwt, newUserInfo));
	}
	
	@ApiOperation(value= "Create User Account and Sign in" , 
				notes= "Provide User Account informationt to create account and log in", 
				response = User.class)
	@PostMapping("/user")
	public ResponseEntity<?> createUserAndSignIn(@RequestBody User user) throws ResourceAlreadyExistsException {
		// Optional <UserCompleteInfo> checkUser =
		// userService.getUserByUsername(user.getUsername());

		Optional<User> checkUser = null;

	

		checkUser = userService.getUserByUserName(user.getUsername());

		//check if exist for account creation or not
		if (checkUser.isPresent()) {
			throw new ResourceAlreadyExistsException(
					"Username " + user.getUsername() + "Already Exists. Please Enter a differnet username");
		}

		final UserCompleteInfo newUserInfo = userService.createUser(user);
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(newUserInfo.getUsername(), user.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(newUserInfo.getUsername());
		final String jwt = jwtUtil.generateTokens(userDetails, true);
		return ResponseEntity.ok(new AuthenticationResponse(jwt, newUserInfo));
	}
	
	@ApiOperation(value= "Get Current User Login" , 
			notes= "Return the current user along with all its reviews", 
			response = User.class)
	@GetMapping("/user")
	public ResponseEntity<?> getCurrentUser() {
		return ResponseEntity.ok(userService.getCurrentUser());
	}
	
	@ApiOperation(value= "Delete Current User" , 
			notes= "Delete the current User Login")
	@DeleteMapping("/user")
	public ResponseEntity<?> deleteUser(@RequestBody User user) {
		repo.deleteById(MyUserDetailsService.getCurrentUserId());
		return ResponseEntity.ok(null);
	}
	
	@ApiOperation(value= "Update User Information" , 
			notes= "Update User information and return updated User", 
			response = User.class)
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		return ResponseEntity.ok(repo.save(user));
	}
}
