package com.soen.empower.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Class Course has all the course related info.
 */
@Entity
public class Course {

	/** The course id. */
	@Id
	@GeneratedValue
	private Long id;
	
	/** The course name. */
	private String name;

	/** The teacher for the course. */
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	/**
	 * Instantiates a new course.
	 */
	public Course(){
		
	}
	
	/**
	 * Instantiates a new course.
	 *
	 * @param id the id
	 * @param name the name
	 * @param teacher the teacher
	 */
	public Course(Long id, String name, Teacher teacher) {
		this.id = id;
		this.name = name;
		this.teacher = teacher;
	}

	/**
	 * Gets the course id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the course id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the teacher who is teaching the course.
	 *
	 * @return the teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * Sets the teacher.
	 *
	 * @param teacher the new teacher
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	/**
	 * Gets the course name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the course name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
