package com.nk.sailpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nk.sailpoint.entity.Employee;
import com.nk.sailpoint.service.EmployeeService;

@RestController
@RequestMapping("/tech/nk")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/add-employee")
	public String addEmployee(@RequestBody Employee employee) {
	return	employeeService.addEmployee(employee);
	}
	
	@GetMapping("/find-all-employee")
	public List<Employee> getEmployees(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber,
			                           @RequestParam(value="pageSize",defaultValue = "2",required = false)Integer pageSize){
		 
		return employeeService.getEmployees(pageNumber,pageSize);
	}
	@GetMapping("/find-employee")
	public Employee getEmployee(@RequestHeader String empId){
		return employeeService.getEmployee(empId);
	}
}
