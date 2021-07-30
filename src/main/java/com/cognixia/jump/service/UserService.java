package com.cognixia.jump.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.responsemodels.UserCompleteInfo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public UserCompleteInfo createUser(User user) {
		return new UserCompleteInfo(repo.save(user));
	}
	
	public UserCompleteInfo getCurrentUser() {
		User user = repo.findById(MyUserDetailsService.getCurrentUserId()).get();
		return new UserCompleteInfo(user);
	}
	
	public UserCompleteInfo updateUser(User user) {
		User preUpdateUser = repo.findById(user.getUserId()).get();
		return new UserCompleteInfo(repo.save(user));
	}
	
}
