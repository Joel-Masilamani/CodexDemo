package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Instructor;
import com.example.studentmanagement.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        return instructorService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        Instructor saved = instructorService.save(instructor);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        return instructorService.findById(id)
                .map(existing -> {
                    existing.setFirstName(instructor.getFirstName());
                    existing.setLastName(instructor.getLastName());
                    existing.setDepartment(instructor.getDepartment());
                    return ResponseEntity.ok(instructorService.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
