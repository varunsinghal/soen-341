package com.soen.empower.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



/**
 * The Class Course.
 */
@Entity
public class Course {

	/** The id. */
	@Id
	@GeneratedValue
	private Long id;
	
	/** The name. */
	private String name;

	/** The teacher. */
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the teacher.
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
