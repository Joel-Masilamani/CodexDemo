package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Enrollment;
import com.example.studentmanagement.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment saved = enrollmentService.save(enrollment);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        return enrollmentService.findById(id)
                .map(existing -> {
                    existing.setStudent(enrollment.getStudent());
                    existing.setCourse(enrollment.getCourse());
                    existing.setSemester(enrollment.getSemester());
                    existing.setGrade(enrollment.getGrade());
                    return ResponseEntity.ok(enrollmentService.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
