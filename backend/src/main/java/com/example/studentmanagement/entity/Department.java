package com.example.studentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Department represents an academic department such as Computer Science or Business.
 * It is a parent entity to instructors and courses.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties({"department", "courses"})
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties({"department", "instructor", "enrollments"})
    private List<Course> courses = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }
}
