package com.example.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.entity.Student;

/**
 * StudentRepository provides data access methods for Student entities.
 * Extending JpaRepository gives basic CRUD and paging support.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
