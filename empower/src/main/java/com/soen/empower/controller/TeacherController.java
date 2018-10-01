package com.soen.empower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soen.empower.entity.Course;
import com.soen.empower.entity.Teacher;
import com.soen.empower.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("")
	public String index() {
		return "redirect:/teacher/home";
	}
	
	@RequestMapping("/home")
	public String teacherHome() {
		return "teacher/home";
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
