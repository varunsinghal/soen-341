package com.soen.empower.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The Class Parent.
 */
@Entity
public class Parent {
	
	/** The id. */
	@Id
	@GeneratedValue
	private Long id;

	/** The students. */
	@OneToMany(mappedBy = "parent")
	private List<Student> students;
	
	/**
	 * Instantiates a new parent.
	 */
	public Parent() {
		
	}

	public Parent(Long id) {
		this.id = id;
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
	 * Gets the students.
	 *
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * Sets the students.
	 *
	 * @param students the new students
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
}
