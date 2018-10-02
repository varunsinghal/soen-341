package com.soen.empower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soen.empower.entity.Card;
import com.soen.empower.entity.Teacher;
import com.soen.empower.entity.User;
import com.soen.empower.service.CardService;
import com.soen.empower.service.TeacherService;
import com.soen.empower.service.UserService;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CardService cardService;
	
	// --------------------------------------------------------------------------------
	
	@RequestMapping("/users")
	public List<User> allUsers(){
		return userService.findAll();
	}
	
	@RequestMapping(value="/users/add", method=RequestMethod.POST)
	public String addUser(@ModelAttribute User user) {
		userService.add(user);
		return "Added successfully";
	}
	
	// --------------------------------------------------------------------------------
	@RequestMapping("/teachers")
	public List<Teacher> allTeachers(){
		return teacherService.findAll();
	}
	
	@RequestMapping(value="/teachers/add", method=RequestMethod.POST)
	public String addTeacher(@ModelAttribute Teacher teacher) {
		teacherService.add(teacher);
		return "Added successfully";
	}
	
	
	// --------------------------------------------------------------------------------
	
	@RequestMapping("/cards")
	public List<Card> allCards(){
		return cardService.fetchAll();
	}
	
	@RequestMapping(value="/cards/add", method=RequestMethod.POST)
	public String addCard(@ModelAttribute Card card) {
		//card.setTeacher(teacherService.findById((long) 1));
		cardService.add(card);
		return "Added";
	}
	
	// --------------------------------------------------------------------------------
}	
