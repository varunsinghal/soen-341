package com.soen.empower.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soen.empower.entity.User;
import com.soen.empower.repository.UserRepository;

public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		for (User user: userRepository.findAll())
			users.add(user);
		return users;
	}
	
	public void add(User user) {
		userRepository.save(user);
	}
	
}
