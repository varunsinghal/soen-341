package com.soen.empower.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soen.empower.entity.Course;

/**
 * The Class CourseController.
 */
@RestController
@RequestMapping("/course")
public class CourseController {
	
	/**
	 * Adds the course.
	 *
	 * @param course the course
	 * @return the string
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addCourse(@RequestBody Course course) {
		
		return "Added successfully";
	}
}
