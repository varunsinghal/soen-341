package com.soen.empower.entity;

import javax.persistence.*;
import java.util.List;

/**
 * The Class Group.
 */
@Entity
@Table(name = "groups")
public class Group {
    
    /** The id. */
    @Id
    @GeneratedValue
    private Long id;

    /** The name. */
    private String name;
    
    /** The description. */
    private String description;

    /** The owner. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    /** The members. */
    @ManyToMany
    @JoinTable
    private List<User> members;

    /**
     * Gets the constructed group id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Constructing the group id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the constructed group name.
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Constructing the group name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the constructed group description.
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Constructing the group description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the constructed group owner.
     *
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Constructing the group owner.
     *
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets the constructed group members.
     *
     */
    public List<User> getMembers() {
        return members;
    }

    /**
     * Constructing the group members.
     *
     */
    public void setMembers(List<User> members) {
        this.members = members;
    }
}
