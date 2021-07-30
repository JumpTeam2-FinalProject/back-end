package com.cognixia.jump.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.responsemodels.AuthenticationRequest;
import com.cognixia.jump.responsemodels.AuthenticationResponse;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.service.UserService;
import com.cognixia.jump.util.JwtUtil;

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
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Username or password is incorrect.", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateTokens(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getCurrentUser() {
//		User user = repo.findById(MyUserDetailsService.getCurrentUserId()).get();
		return ResponseEntity.ok(userService.getCurrentUser());
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<?> deleteUser(@RequestBody User user) {
		repo.deleteById(MyUserDetailsService.getCurrentUserId());
		return ResponseEntity.ok(null);
	}
	
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		return ResponseEntity.ok(repo.save(user));
	}
}
