package com.soen.empower.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * This entity class gives the Student attributes.
 * 
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="student")
	private List<Academic> academics;
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Parent parent;

	/**
	 * Instantiates a new student.
	 */
	public Student() {

	}

	/**
	 * Instantiates a new student.
	 *
	 * @param id the id
	 * @param name the name
	 */
	public Student(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets the constructed student id.
	 *
	 * @return the student id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Constructing the student id.
	 *
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the constructed student name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Constructing the student name.
	 *
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the constructed student academics.
	 *
	 * @return the academics
	 */
	public List<Academic> getAcademics() {
		return academics;
	}

	/**
	 * Constructing the student academics.
	 *
	 */
	public void setAcademics(List<Academic> academics) {
		this.academics = academics;
	}
}
