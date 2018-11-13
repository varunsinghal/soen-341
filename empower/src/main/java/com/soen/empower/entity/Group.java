package com.soen.empower.entity;

import javax.persistence.*;
import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * This Entity Class Group.
 *
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "groups")
public class Group {

    /**
     * The id.
     */
    @Id
    @GeneratedValue
    private Long id;


    /**
     * The name.
     */
    private String name;


    /**
     * The description.
     */
    private String description;


    /**
     * The owner.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    /**
     * The members.
     */
    @ManyToMany
    @JoinTable
    private List<User> members;

    /**
     * The admins.
     */
    @ManyToMany
    @JoinTable
    private List<User> admins;

    /**
     * The requests.
     */
    @ManyToMany
    @JoinTable
    private List<User> requests;


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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the owner.
     *
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the owner.
     *
     * @param owner the new owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets the members.
     *
     * @return the members
     */
    public List<User> getMembers() {
        return members;
    }

    /**
     * Sets the members.
     *
     * @param members the new members
     */
    public void setMembers(List<User> members) {
        this.members = members;
    }

    /**
     * Gets the admins.
     *
     * @return the admins
     */
    public List<User> getAdmins() {
        return admins;
    }

    /**
     * Sets the admins.
     *
     * @param admins the new admins
     */
    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    /**
     * Gets the requests.
     *
     * @return the requests
     */
    public List<User> getRequests() {
        return requests;
    }

    /**
     * Sets the requests.
     *
     * @param requests the new requests
     */
    public void setRequests(List<User> requests) {
        this.requests = requests;
    }
}
