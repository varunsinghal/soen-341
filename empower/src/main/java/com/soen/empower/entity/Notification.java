package com.soen.empower.entity;

import javax.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue
    private Long id;
    private String HTML;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    Notification(){
    }

    public Notification(String HTML, User user, String type) {
        this.HTML = HTML;
        this.user = user;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHTML() {
        return HTML;
    }

    public void setHTML(String HTML) {
        this.HTML = HTML;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}