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
 */
@Entity
public class Comment {
	
	/** The id. */
	@Id
	@GeneratedValue
	private Long id;
	
	/** The text. */
	private String text;
	
	/** The user. */
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	/** The card. */
	@ManyToOne
	@JoinColumn(name="card_id")
	private Card card;
	
	/** The created. */
	private Date created = new Date();
	
	/** The updated. */
	private Date updated = new Date();

	/**
	 * Sets the last update.
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
	 * Gets the comment id.
	 *
	 * @return the comment id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the comment id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the comment text.
	 *
	 * @return the comment text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the comment text.
	 *
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the user related to the comment.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the comment card.
	 *
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Sets the comment card.
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
