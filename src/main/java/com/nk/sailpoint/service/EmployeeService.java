package com.nk.sailpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nk.sailpoint.entity.Employee;
import com.nk.sailpoint.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public String addEmployee(Employee employee) {
		try {
		employeeRepository.save(employee);	 
		}catch (Exception e) {
			System.out.println("connection issue while adding data");
			 return "connection issue";
		}
		return "data added success fully";
	}

	public List<Employee> getEmployees(Integer pageNumber, Integer pageSize) {
		 
		 PageRequest pageRequest=PageRequest.of(pageNumber, pageSize);
		 Page<Employee> pageEmployee = employeeRepository.findAll(pageRequest);
		return pageEmployee.getContent();
		//return employeeRepository.findAll();
	}

	public Employee getEmployee(String empId) {
		return employeeRepository.findById(empId).orElseThrow();
		 
	}

}
