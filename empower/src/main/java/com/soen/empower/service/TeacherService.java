package com.soen.empower.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soen.empower.entity.Course;
import com.soen.empower.entity.Teacher;
import com.soen.empower.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	public List<Teacher> findAll(){
		List<Teacher> teachers = new ArrayList<>();
		for(Teacher teacher: teacherRepository.findAll())
			teachers.add(teacher);
		return teachers;
	}
	
	public void add(Teacher teacher) {
		teacherRepository.save(teacher);
	}

	public List<Course> findCoursesByTeacherId(Long id) {
		Teacher teacher = teacherRepository.findById(id);
		return teacher.getCourses();
	}
	
	public Teacher findById(Long id) {
		return teacherRepository.findById(id);
	}

	
}
