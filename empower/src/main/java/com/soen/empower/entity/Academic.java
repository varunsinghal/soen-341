package com.soen.empower.entity;

import javax.persistence.*;

/**
 * The Class Academic has the academic info of a student 
 * 
 */
@Entity
public class Academic {

    /** The id. */
    @Id
    @GeneratedValue
    private Long id;

    /** The grade. */
    private String grade;

    /** The student  */
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    /**
     * Get the constructed id 
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Constructing the id component
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the constructed grade.
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Constructing the grade component.
     *
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Get the constructed student from the Student.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Constructing the student component
     */
    public void setStudent(Student student) {
        this.student = student;
    }

}
