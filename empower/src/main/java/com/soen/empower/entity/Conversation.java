package com.soen.empower.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * The Class Conversation stores the conversations between two users.
 */
@Entity
public class Conversation {
    
    /** The conversation id. */
    @Id
    @GeneratedValue
    private Long id;

    /** The user who initiated the conversation*/
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** The other user involved in the conversation. */
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

    public Conversation(Long id, User user, User otherUser, Long lastMessageId) {
        this.id = id;
        this.user = user;
        this.otherUser = otherUser;
        this.lastMessageId = lastMessageId;
    }

    /**
     * Get the constructed conversation id.
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * Constructing the conversation id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user who initiated the conversation.
     */
    public User getUser() {
        return user;
    }

    /**
     * Constructing the user.
     *
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the other user who is involved the conversation.
     *
     */
    public User getOtherUser() {
        return otherUser;
    }

    /**
     * Constructing the other user.
     *
     */
    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }

    /**
     * Gets the constructed last message id.
     *
     * @return the last message id
     */
    public Long getLastMessageId() {
        return lastMessageId;
    }

    /**
     * Constructing the last message id.
     *
     * @param lastMessageId the new last message id
     */
    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    /**
     * Gets the updated date of the conversation.
     *
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }
}
