package com.soen.empower.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soen.empower.entity.Course;
import com.soen.empower.repository.CourseRepository;

/**
 * The Class CourseService.
 */
@Service
public class CourseService {

	/** The course repository. */
	@Autowired
	private CourseRepository courseRepository;
	
	/**
	 * Adds the course.
	 *
	 * @param course the course
	 */
	public void addCourse(Course course) {
			courseRepository.save(course);
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Course> findAll(){
		List<Course> courses = new ArrayList<>();
		for(Course course: courseRepository.findAll())
			courses.add(course);
		return courses;
	}
	
	/**
	 * Find by teacher id.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Course> findByTeacherId(Long id){
		return courseRepository.findByTeacherId(id);
	}
}
