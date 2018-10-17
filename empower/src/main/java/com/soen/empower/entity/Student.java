package com.soen.empower.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * The Class Student.
 */
@Entity
public class Student {

	/** The id. */
	@Id
	@GeneratedValue
	private Long id;
	
	/** The name. */
	private String name;
	
	/** The academics. */
	@OneToMany(mappedBy="student")
	private List<Academic> academics;
	
	/** The parent. */
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

	/**
	 * Gets the academics.
	 *
	 * @return the academics
	 */
	public List<Academic> getAcademics() {
		return academics;
	}

	/**
	 * Sets the academics.
	 *
	 * @param academics the new academics
	 */
	public void setAcademics(List<Academic> academics) {
		this.academics = academics;
	}
}
