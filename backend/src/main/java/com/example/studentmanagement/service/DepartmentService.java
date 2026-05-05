package com.example.studentmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.studentmanagement.entity.Department;
import com.example.studentmanagement.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
