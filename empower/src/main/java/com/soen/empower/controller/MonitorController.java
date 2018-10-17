package com.soen.empower.controller;

import java.util.List;

import com.soen.empower.entity.Message;
import com.soen.empower.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.soen.empower.entity.Card;
import com.soen.empower.entity.Teacher;
import com.soen.empower.entity.User;
import com.soen.empower.service.CardService;
import com.soen.empower.service.TeacherService;
import com.soen.empower.service.UserService;

/**
 * The Class MonitorController.
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The teacher service. */
	@Autowired
	private TeacherService teacherService;
	
	/** The card service. */
	@Autowired
	private CardService cardService;

	/** The message service. */
	@Autowired
	private MessageService messageService;
	
	// --------------------------------------------------------------------------------
	
	/**
	 * All users.
	 *
	 * @return the list
	 */
	@RequestMapping("/users")
	public List<User> allUsers(){
		return userService.findAll();
	}
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the string
	 */
	@RequestMapping(value="/users/add", method=RequestMethod.POST)
	public String addUser(@ModelAttribute User user) {
		userService.add(user);
		return "Added successfully";
	}
	
	/**
	 * All teachers.
	 *
	 * @return the list
	 */
	// --------------------------------------------------------------------------------
	@RequestMapping("/teachers")
	public List<Teacher> allTeachers(){
		return teacherService.findAll();
	}
	
	/**
	 * Adds the teacher.
	 *
	 * @param teacher the teacher
	 * @return the string
	 */
	@RequestMapping(value="/teachers/add", method=RequestMethod.POST)
	public String addTeacher(@ModelAttribute Teacher teacher) {
		teacherService.add(teacher);
		return "Added successfully";
	}
	
	
	// --------------------------------------------------------------------------------
	
	/**
	 * All cards.
	 *
	 * @return the list
	 */
	@RequestMapping("/cards")
	public List<Card> allCards(){
		return cardService.fetchAll();
	}
	
	/**
	 * Adds the card.
	 *
	 * @param card the card
	 * @return the string
	 */
	@RequestMapping(value="/cards/add", method=RequestMethod.POST)
	public String addCard(@ModelAttribute Card card) {
		//card.setTeacher(teacherService.findById((long) 1));
		cardService.add(card);
		return "Added";
	}
	
	// --------------------------------------------------------------------------------

	/**
	 * All messages.
	 *
	 * @param conId the con id
	 * @return the list
	 */
	@RequestMapping("/messages")
	public List<Message> allMessages(@RequestParam(value="id") int conId){
		return messageService.fetch((long) conId);
	}
}	
