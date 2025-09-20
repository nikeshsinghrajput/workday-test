package com.nk.sailpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nk.sailpoint.entity.Employee;
import com.nk.sailpoint.entity.PageableResponse;
import com.nk.sailpoint.repository.EmployeeRepository;

import com.nk.sailpoint.util.Helper;

import org.springframework.data.domain.Pageable;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
//	@Autowired
//	PagingAndSortingRepository pagingAndSortingRepository;
	
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
		 
	}

	public Employee getEmployee(String empId) {
		return employeeRepository.findById(empId).orElseThrow();
		 
	}
	public PageableResponse<Employee> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
	    Sort sort = (sortDir.equalsIgnoreCase("desc")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	    Page<Employee> employeePage = employeeRepository.findAll(pageable);

	    return Helper.getPageableResponse(employeePage);
	}


}
