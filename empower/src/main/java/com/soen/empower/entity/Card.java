package com.soen.empower.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String text;

    @ManyToOne
    @JoinColumn(name = "belongs_to_id")
    private User belongsTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "card")
    private List<Comment> comments;

    @OneToMany(mappedBy = "card")
    private List<Like> likes;

    @OneToMany(mappedBy = "card")
    private List<Dislike> dislikes;


    private Date created = new Date();
    private Date updated = new Date();

    /**
     * Sets the last update.
     */
    @PreUpdate
    public void setLastUpdate() {
        this.updated = new Date();
    }

    public Card() {

    }

    public Card(Long id, String title, String text, User user) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setText(String text) {
        this.text = text;
    }


    public List<Comment> getComments() {
        return comments;
    }


    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public User getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(User belongsTo) {
        this.belongsTo = belongsTo;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Dislike> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<Dislike> dislikes) {
        this.dislikes = dislikes;
    }
}
