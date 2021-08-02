package com.cognixia.jump.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Review;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.responsemodels.UserCompleteInfo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public UserCompleteInfo createUser(User user) {
		User newUser = repo.save(user);
		newUser.setReviews(new ArrayList<Review>());
		return new UserCompleteInfo(newUser);
	}
	
	public UserCompleteInfo getCurrentUser() {
		return getUserById(MyUserDetailsService.getCurrentUserId());
	}
	
	public UserCompleteInfo updateUser(User user) {
		User preUpdateUser = repo.findById(user.getUserId()).get();
		return new UserCompleteInfo(repo.save(user));
	}
	
	public UserCompleteInfo getUserById(Integer userId) {
		User user = repo.findById(userId).get();
		return new UserCompleteInfo(user);
	}
	
	public UserCompleteInfo getUserByUsername(String username) {
		User user = repo.findByUsername(username).get();
		return new UserCompleteInfo(user);
	}
	
	public Optional <User> getUserByUserName(String username) {
		
		Optional <User> user = repo.findByUsername(username);
		
		return user;
		
	}
	
}
