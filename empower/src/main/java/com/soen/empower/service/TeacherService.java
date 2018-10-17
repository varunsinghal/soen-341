package com.soen.empower.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soen.empower.entity.Course;
import com.soen.empower.entity.Teacher;
import com.soen.empower.repository.TeacherRepository;

/**
 * The Class TeacherService.
 */
@Service
public class TeacherService {

	/** The teacher repository. */
	@Autowired
	private TeacherRepository teacherRepository;
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Teacher> findAll(){
		List<Teacher> teachers = new ArrayList<>();
		for(Teacher teacher: teacherRepository.findAll())
			teachers.add(teacher);
		return teachers;
	}
	
	/**
	 * Adds the.
	 *
	 * @param teacher the teacher
	 */
	public void add(Teacher teacher) {
		teacherRepository.save(teacher);
	}

	/**
	 * Find courses by teacher id.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Course> findCoursesByTeacherId(Long id) {
		Teacher teacher = teacherRepository.findById(id);
		return teacher.getCourses();
	}
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the teacher
	 */
	public Teacher findById(Long id) {
		return teacherRepository.findById(id);
	}

	
}
