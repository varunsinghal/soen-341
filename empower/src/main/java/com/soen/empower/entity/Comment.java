package com.soen.empower.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;

/**
 * The Class Comment which allows the user to comment on posts.
 * 
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String text;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="card_id")
	private Card card;
	
	private Date created = new Date();
	
	private Date updated = new Date();

	/**
	 * Constructing the last update component.
	 */
	@PreUpdate
	public void setLastUpdate() {  this.updated = new Date(); }
	
	/**
	 * Instantiates a new comment.
	 */
	public Comment() {
		
	}

	public Comment(Long id, String text, User user, Card card) {
		this.id = id;
		this.text = text;
		this.user = user;
		this.card = card;
	}

	/**
	 * Get the constructed comment id.
	 *
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Constructing the comment id.
	 *
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the constructed comment text.
	 *
	 */
	public String getText() {
		return text;
	}

	/**
	 * Constructing the comment text.
	 *
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Get the constructed user related to the comment.
	 *
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Constructing the user component.
	 *
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Get the constructed comment card.
	 *
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Constructing the comment card component.
	 *
	 * @param card the new card
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	/**
	 * Gets the current date.
	 *
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets the current date.
	 *
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Gets the updated date.
	 *
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * Sets the updated date.
	 *
	 * @param updated the new updated
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	

}
