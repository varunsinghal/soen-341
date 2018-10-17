package com.soen.empower.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * The Class Conversation.
 */
@Entity
public class Conversation {
    
    /** The id. */
    @Id
    @GeneratedValue
    private Long id;

    /** The user. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** The other user. */
    @ManyToOne
    @JoinColumn(name = "other_user_id")
    private User otherUser;

    /** The last message id. */
    private Long lastMessageId;

    /** The updated. */
    private Date updated = new Date();

    /**
     * Sets the last update.
     */
    @PreUpdate
    public void setLastUpdate() {  this.updated = new Date(); }

    /**
     * Instantiates a new conversation.
     */
    public Conversation(){

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
     * Gets the other user.
     *
     * @return the other user
     */
    public User getOtherUser() {
        return otherUser;
    }

    /**
     * Sets the other user.
     *
     * @param otherUser the new other user
     */
    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }

    /**
     * Gets the last message id.
     *
     * @return the last message id
     */
    public Long getLastMessageId() {
        return lastMessageId;
    }

    /**
     * Sets the last message id.
     *
     * @param lastMessageId the new last message id
     */
    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    /**
     * Gets the updated.
     *
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }
}
