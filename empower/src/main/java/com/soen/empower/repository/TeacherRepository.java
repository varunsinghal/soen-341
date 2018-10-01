package com.soen.empower.repository;

import org.springframework.data.repository.CrudRepository;

import com.soen.empower.entity.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, String>{
	
	public Teacher findById(Long id);

}
