package com.soen.empower.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Teacher {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(mappedBy = "teacher")
	private List<Course> courses;
	
	@OneToMany(mappedBy = "teacher")
	private List<Card> cards;

	public Teacher() {

	}
	
	public Teacher(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	


}
