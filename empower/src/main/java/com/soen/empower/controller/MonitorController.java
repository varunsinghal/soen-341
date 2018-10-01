package com.soen.empower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soen.empower.entity.User;
import com.soen.empower.service.UserService;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public List<User> allUsers(){
		return userService.findAll();
	}
	
	@RequestMapping(value="/users/add", method=RequestMethod.POST)
	public String addUser(@ModelAttribute User user) {
		userService.add(user);
		return "Added successfully";
	}
}	
