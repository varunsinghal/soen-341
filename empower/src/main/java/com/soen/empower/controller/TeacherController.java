package com.soen.empower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.soen.empower.entity.Card;
import com.soen.empower.entity.Comment;
import com.soen.empower.service.CardService;
import com.soen.empower.service.CommentService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private CardService cardService;

	@RequestMapping("")
	public String index() {
		return "redirect:/teacher/home";
	}

	@RequestMapping("/home")
	public ModelAndView teacherHome() {
		ModelAndView model = new ModelAndView("teacher/home");
		model.addObject("cards", cardService.fetchAll());
		return model;
	}

	@RequestMapping(value="/addPost", method=RequestMethod.POST)
	public String addPost(@ModelAttribute Card card) {
		cardService.add(card);
		return "redirect:/teacher/home";
	}
	
	@RequestMapping("/addComment")
	public String addComment(@ModelAttribute Comment comment) {
		commentService.add(comment);
		return "redirect:/teacher/home";
	}

//	@RequestMapping("/all")
//	public List<Teacher> fetchAll(){
//		Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(auth2.getName());
//		System.out.println(auth2.getAuthorities());
//		return teacherService.findAll();
//	}
//	
//	@RequestMapping(value="/add", method=RequestMethod.POST)
//	public String addTeacher(@ModelAttribute Teacher teacher) {
//		teacherService.add(teacher);
//		return "Added successfully";
//	}
//	
//	@RequestMapping(value="/{id}")
//	public List<Course> fetchCourses( @PathVariable Long id) {
//		return teacherService.findCoursesByTeacherId(id);
//	}
}
