package com.soen.empower.entity;

import javax.persistence.*;
import java.util.List;

/**
 * This Entity Class Group.
 * 
 * @version 1.0
 * @since 1.0
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

    /** The members. */
    @OneToMany(mappedBy = "group")
    private List<Member> members;

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
