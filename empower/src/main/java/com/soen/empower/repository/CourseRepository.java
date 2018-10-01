package com.soen.empower.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.soen.empower.entity.Course;

public interface CourseRepository extends CrudRepository<Course, String>{
	public List<Course> findByTeacherId(Long id);
}
