package com.soen.empower.entity;

import javax.persistence.*;

@Entity
public class Friend {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "other_user_id")
    private User otherUser;

    private int enabled = 0;

    public Friend(){
    }

    public Friend(Long id, User user, User otherUser, int enabled) {
        this.id = id;
        this.user = user;
        this.otherUser = otherUser;
        this.enabled = enabled;
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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
