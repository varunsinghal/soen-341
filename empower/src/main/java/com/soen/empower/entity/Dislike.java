package com.soen.empower.entity;

import javax.persistence.*;

/**
 * The Class Dislike gives user the functionality to dislike a post.
 * 
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Dislike {

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
     * Instantiates a new dislike.
     */
    public Dislike(){
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
