package com.soen.empower.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The Parent entity class depict the attributes of a parent .
 * 
 * @version 1.0
 * @since 1.0
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
	 * Gets the constructed parent id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Constructing the parent id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the constructed students list.
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * Constructing the students list.
	 *
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
}
