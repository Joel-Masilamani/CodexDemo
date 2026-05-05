package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Student REST endpoints.
 * Exposes CRUD operations for students over HTTP.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * GET /api/students
     * Returns all students.
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    /**
     * GET /api/students/{id}
     * Returns a student by ID, or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /api/students
     * Creates a new student.
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student saved = studentService.save(student);
        return ResponseEntity.ok(saved);
    }

    /**
     * PUT /api/students/{id}
     * Updates an existing student, or returns 404 if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.findById(id)
                .map(existing -> {
                    existing.setFirstName(student.getFirstName());
                    existing.setLastName(student.getLastName());
                    existing.setEmail(student.getEmail());
                    return ResponseEntity.ok(studentService.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/students/{id}
     * Deletes a student by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
