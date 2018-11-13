package com.soen.empower.entity;

import javax.persistence.*;
import java.util.List;


/**
 * Entity class which depict the attributes of user.
 *
 * @version 1.3
 * @since 1.0
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * The id.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The username.
     */
    private String username;

    /**
     * The password.
     */
    private String password;

    /**
     * The enabled.
     */
    private int enabled = 1;

    /**
     * The full name.
     */
    private String fullName;

    /**
     * The description.
     */
    private String description;

    /**
     * The email.
     */
    private String email;

    /**
     * The dob.
     */
    private String dob;

    /**
     * The role.
     */
    private String role;

    /**
     * The likes.
     */
    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    /**
     * The dislikes.
     */
    @OneToMany(mappedBy = "user")
    private List<Dislike> dislikes;

    /**
     * The teacher.
     */
    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    /**
     * The parent.
     */
    @OneToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * Instantiates a new user.
     *
     * @param id       the id
     * @param username the username
     * @param password the password
     * @param role     the role
     * @param teacher  the teacher
     * @param parent   the parent
     */
    public User(Long id, String username, String password, String role, Teacher teacher, Parent parent) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.setRole(role);
        this.parent = parent;
        this.teacher = teacher;
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
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the teacher.
     *
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Sets the teacher.
     *
     * @param teacher the new teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Gets the parent.
     *
     * @return the parent
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * Sets the parent.
     *
     * @param parent the new parent
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    /**
     * Gets the full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name.
     *
     * @param fullName the new full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the dob.
     *
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets the dob.
     *
     * @param dob the new dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * Gets the likes.
     *
     * @return the likes
     */
    public List<Like> getLikes() {
        return likes;
    }

    /**
     * Sets the likes.
     *
     * @param likes the new likes
     */
    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    /**
     * Gets the dislikes.
     *
     * @return the dislikes
     */
    public List<Dislike> getDislikes() {
        return dislikes;
    }

    /**
     * Sets the dislikes.
     *
     * @param dislikes the new dislikes
     */
    public void setDislikes(List<Dislike> dislikes) {
        this.dislikes = dislikes;
    }

}
