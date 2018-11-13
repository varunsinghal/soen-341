package com.soen.empower.entity;

import javax.persistence.*;

/**
 * The Class Notification.
 */
@Entity
public class Notification {

    /**
     * The id.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The html.
     */
    private String HTML;

    /**
     * The type.
     */
    private String type;

    /**
     * The user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Instantiates a new notification.
     */
    Notification() {
    }

    /**
     * Instantiates a new notification.
     *
     * @param HTML the html
     * @param user the user
     * @param type the type
     */
    public Notification(String HTML, User user, String type) {
        this.HTML = HTML;
        this.user = user;
        this.type = type;
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
     * Gets the html.
     *
     * @return the html
     */
    public String getHTML() {
        return HTML;
    }

    /**
     * Sets the html.
     *
     * @param HTML the new html
     */
    public void setHTML(String HTML) {
        this.HTML = HTML;
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
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }
}
