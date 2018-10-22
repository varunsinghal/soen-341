package com.soen.empower.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * The Class Teacher.
 */
@Entity
public class Teacher {

    /**
     * The id.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The courses.
     */
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    /**
     * Instantiates a new teacher.
     */
    public Teacher() {

    }

    public Teacher(Long id) {
        this.id = id;
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
     * Gets the courses.
     *
     * @return the courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the courses.
     *
     * @param courses the new courses
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
