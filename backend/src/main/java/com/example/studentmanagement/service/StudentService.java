package com.example.studentmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;

/**
 * Service layer for Student entities.
 * This class contains business logic and delegates persistence to StudentRepository.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Return all students in the database.
     */
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    /**
     * Return a single student by its ID.
     */
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    /**
     * Save a new student or update an existing student.
     */
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Delete a student by ID.
     */
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
