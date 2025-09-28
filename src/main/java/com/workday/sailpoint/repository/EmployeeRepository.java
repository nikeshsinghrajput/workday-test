package com.workday.sailpoint.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.workday.sailpoint.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	Page<Employee> findAll(Pageable pageable);
}
