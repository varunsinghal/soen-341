package com.soen.empower.repository;

import org.springframework.data.repository.CrudRepository;

import com.soen.empower.entity.Teacher;

/**
 * The Interface TeacherRepository.
 */
public interface TeacherRepository extends CrudRepository<Teacher, String>{
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the teacher
	 */
	public Teacher findById(Long id);

}
