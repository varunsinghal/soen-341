package com.soen.empower.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Course {

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	public Course(){
		
	}
	
	public Course(Long id, String name, Teacher teacher) {
		this.id = id;
		this.name = name;
		this.teacher = teacher;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
