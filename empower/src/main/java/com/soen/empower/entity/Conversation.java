package com.soen.empower.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Conversation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "other_user_id")
    private User otherUser;

    private Long lastMessageId;

    private Date updated = new Date();

    @PreUpdate
    public void setLastUpdate() {  this.updated = new Date(); }

    public Conversation(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public Date getUpdated() {
        return updated;
    }
}
