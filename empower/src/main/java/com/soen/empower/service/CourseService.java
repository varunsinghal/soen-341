package com.soen.empower.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soen.empower.entity.Course;
import com.soen.empower.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	public void addCourse(Course course) {
			courseRepository.save(course);
	}
	
	public List<Course> findAll(){
		List<Course> courses = new ArrayList<>();
		for(Course course: courseRepository.findAll())
			courses.add(course);
		return courses;
	}
	
	public List<Course> findByTeacherId(Long id){
		return courseRepository.findByTeacherId(id);
	}
}
