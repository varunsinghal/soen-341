package com.soen.empower.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.soen.empower.entity.Course;
/*
 * The interface CourseRepository
 */
public interface CourseRepository extends CrudRepository<Course, String>{
	/*
	 * Find list of courses by TeacherId
	 * @param long id
	 * @return list of courses
	 */
	public List<Course> findByTeacherId(Long id);
}
