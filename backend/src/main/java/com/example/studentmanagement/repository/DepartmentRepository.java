package com.example.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
