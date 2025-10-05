package com.workday.sailpoint.controller;

import java.nio.file.Path;

import com.workday.sailpoint.util.EmployeeCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.workday.sailpoint.entity.Employee;
import com.workday.sailpoint.entity.PageableResponse;
import com.workday.sailpoint.service.EmployeeService;

@RestController
@RequestMapping("/workday")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	private EmployeeCSVService employeeCSVService;
	
	@PostMapping
	public String addEmployee(@RequestBody Employee employee) {
	return	employeeService.addEmployee(employee);
	}

	@GetMapping("/{employeeId}")
	public Employee getEmployee(@PathVariable String employeeId){
		return employeeService.getEmployee(employeeId);
	}

	@GetMapping
    public ResponseEntity<PageableResponse<Employee>> getAll(@RequestParam(value = "pageNumber", defaultValue = "0", required = false)int pageNumber,
                                                               @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
                                                               @RequestParam(value = "sortBy", defaultValue = "employeeNumber",required = false)String sortBy,
                                                               @RequestParam(value = "sortDir", defaultValue = "asc",required = false)String sortDir
    ){
        PageableResponse<Employee> pageableResponse = employeeService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


	@PostMapping("/import")
	public ResponseEntity<String> importCSV(@RequestParam("filePath") String filePath) {
		try {
			Path path = Path.of(filePath);
			String result = employeeCSVService.importCSV(path);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

	@PutMapping("/{employeeNumber}")
	public ResponseEntity<Employee> updateEmployee(
			@PathVariable String employeeNumber,
			@RequestBody Employee updatedEmployee) {
		Employee employee = employeeService.updateEmployee(employeeNumber, updatedEmployee);
		return ResponseEntity.ok(employee);
	}
}
