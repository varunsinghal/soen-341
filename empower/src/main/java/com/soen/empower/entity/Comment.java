package com.soen.empower.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;

/**
 * The Class Comment.
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
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the user.
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
	 * Gets the card.
	 *
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Sets the card.
	 *
	 * @param card the new card
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Gets the updated.
	 *
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * Sets the updated.
	 *
	 * @param updated the new updated
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	

}
