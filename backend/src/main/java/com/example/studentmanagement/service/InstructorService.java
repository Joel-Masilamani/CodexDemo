package com.example.studentmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.studentmanagement.entity.Instructor;
import com.example.studentmanagement.repository.InstructorRepository;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> findById(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }
}
