package com.soen.empower.entity;

import javax.persistence.*;

/**
 * The Entity Class contains the logic for like functionality.
 * 
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "likes")
public class Like {

    /** The id. */
    @Id
    @GeneratedValue
    private Long id;

    /** The card. */
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    /** The user. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Instantiates a new like.
     */
    public Like(){
    }

    /**
     * Instantiates a new like.
     *
     * @param id the id
     * @param card the card
     * @param user the user
     */
    public Like(Long id, Card card, User user) {
        this.id = id;
        this.card = card;
        this.user = user;
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
}
