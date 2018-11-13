package com.soen.empower.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * The Class Comment which allows the user to comment on posts.
 *
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Comment {

    /**
     * The id.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The text.
     */
    private String text;

    /**
     * The user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The card.
     */
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    /**
     * The created.
     */
    private Date created = new Date();

    /**
     * The updated.
     */
    private Date updated = new Date();

    /**
     * Constructing the last update component.
     */
    @PreUpdate
    public void setLastUpdate() {
        this.updated = new Date();
    }

    /**
     * Instantiates a new comment.
     */
    public Comment() {

    }

    /**
     * Instantiates a new comment.
     *
     * @param id   the id
     * @param text the text
     * @param user the user
     * @param card the card
     */
    public Comment(Long id, String text, User user, Card card) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.card = card;
    }

    /**
     * Get the constructed comment id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Constructing the comment id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the constructed comment text.
     */
    public String getText() {
        return text;
    }

    /**
     * Constructing the comment text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get the constructed user related to the comment.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Constructing the user component.
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
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Gets the updated date.
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * Sets the updated date.
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }


}
