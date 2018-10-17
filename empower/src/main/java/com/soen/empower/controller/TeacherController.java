package com.soen.empower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class TeacherController.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

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
