package com.sailpoint.workday.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sailpoint.workday.entity.Employee;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	Page<Employee> findAll(Pageable pageable);
	@Query(
			value = "SELECT COALESCE(MAX(CAST(employee_number AS UNSIGNED)), 0) " +
					"FROM hr_workday.employee " +
					"WHERE employee_number REGEXP '^[0-9]+$'",
			nativeQuery = true
	)
	Long findMaxEmployeeNumber();
}
