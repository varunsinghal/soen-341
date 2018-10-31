package com.soen.empower.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * This entity Class gives the information about the Teacher.
 * 
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;

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
     * Gets the constructed teacher id.
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * constructing the teacher id.
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the constructed teacher courses.
     *
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Constructing the teacher courses.
     *
     * @param courses the new courses
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
