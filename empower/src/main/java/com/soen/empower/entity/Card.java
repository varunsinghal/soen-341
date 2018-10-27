package com.soen.empower.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The Class Card.
 */
@Entity
public class Card {

    /**
     * The id.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The title.
     */
    private String title;

    /**
     * The text.
     */
    private String text;

    @ManyToOne
    @JoinColumn(name = "belongs_to_id")
    private User belongsTo;

    /**
     * The user.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The comments.
     */
    @OneToMany(mappedBy = "card")
    private List<Comment> comments;

    /**
     * The created.
     */
    private Date created = new Date();

    /**
     * The updated.
     */
    private Date updated = new Date();

    /**
     * Sets the last update.
     */
    @PreUpdate
    public void setLastUpdate() {
        this.updated = new Date();
    }

    /**
     * Instantiates a new card.
     */
    public Card() {

    }

    public Card(Long id, String title, String text, User user) {
        this.id = id;
        this.title = title;
        this.text = text;
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
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * Sets the text.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the comments.
     *
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets the comments.
     *
     * @param comments the new comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    public User getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(User belongsTo) {
        this.belongsTo = belongsTo;
    }
}
