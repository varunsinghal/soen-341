package com.soen.empower.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.soen.empower.entity.Course;

/**
 * The Interface CourseRepository.
 */
public interface CourseRepository extends CrudRepository<Course, String>{
	
	/**
	 * Find by teacher id.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Course> findByTeacherId(Long id);
}
