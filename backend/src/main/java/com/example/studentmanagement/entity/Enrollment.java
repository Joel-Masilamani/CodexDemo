package com.example.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Enrollment defines the many-to-many relationship between Student and Course.
 * It also stores additional details such as semester and grade.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties("enrollments")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties("enrollments")
    private Course course;

    @Column(nullable = false)
    private String semester;

    @Column
    private String grade;

    public Enrollment(Student student, Course course, String semester, String grade) {
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = grade;
    }
}
