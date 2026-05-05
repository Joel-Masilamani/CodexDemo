package com.example.studentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Course represents an academic class that students can enroll in.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties({"instructors", "courses"})
    private Department department;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnoreProperties({"department", "courses"})
    private Instructor instructor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("course")
    private List<Enrollment> enrollments = new ArrayList<>();

    public Course(String name, String code, Department department, Instructor instructor) {
        this.name = name;
        this.code = code;
        this.department = department;
        this.instructor = instructor;
    }
}
