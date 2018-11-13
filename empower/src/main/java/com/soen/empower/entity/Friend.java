package com.soen.empower.entity;

import javax.persistence.*;

/**
 * The Class Friend.
 */
@Entity
public class Friend {

    /**
     * The id.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The other user.
     */
    @ManyToOne
    @JoinColumn(name = "other_user_id")
    private User otherUser;

    /**
     * The enabled.
     */
    private int enabled = 0;

    /**
     * Instantiates a new friend.
     */
    public Friend() {
    }

    /**
     * Instantiates a new friend.
     *
     * @param id        the id
     * @param user      the user
     * @param otherUser the other user
     * @param enabled   the enabled
     */
    public Friend(Long id, User user, User otherUser, int enabled) {
        this.id = id;
        this.user = user;
        this.otherUser = otherUser;
        this.enabled = enabled;
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
     * Gets the enabled.
     *
     * @return the enabled
     */
    public int getEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled the new enabled
     */
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
