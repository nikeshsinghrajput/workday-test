package com.workday.sailpoint.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.workday.sailpoint.entity.Employee;
import com.workday.sailpoint.entity.PageableResponse;
import com.workday.sailpoint.repository.EmployeeRepository;

import com.workday.sailpoint.util.Helper;

import org.springframework.data.domain.Pageable;

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

	public Employee getEmployee(String empId) {
		return employeeRepository.findById(empId).orElseThrow();
		 
	}

	public PageableResponse<Employee> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
	    Sort sort = (sortDir.equalsIgnoreCase("desc")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	    Page<Employee> employeePage = employeeRepository.findAll(pageable);
	    return Helper.getPageableResponse(employeePage);
	}

	public Employee updateEmployee(String employeeNumber, Employee updatedEmployee) {
		Optional<Employee> existingEmployeeOpt = employeeRepository.findById(employeeNumber);

		if (existingEmployeeOpt.isEmpty()) {
			throw new RuntimeException("Employee with number " + employeeNumber + " not found.");
		}

		Employee existingEmployee = existingEmployeeOpt.get();

		// Update only if values are provided (non-null)
		if (updatedEmployee.getAddressLine1() != null) existingEmployee.setAddressLine1(updatedEmployee.getAddressLine1());
		if (updatedEmployee.getAddressWork() != null) existingEmployee.setAddressWork(updatedEmployee.getAddressWork());
		if (updatedEmployee.getBuildingCode() != null) existingEmployee.setBuildingCode(updatedEmployee.getBuildingCode());
		if (updatedEmployee.getBusinessUnitId() != null) existingEmployee.setBusinessUnitId(updatedEmployee.getBusinessUnitId());
		if (updatedEmployee.getRegion() != null) existingEmployee.setRegion(updatedEmployee.getRegion());
		if (updatedEmployee.getEmployeeClass() != null) existingEmployee.setEmployeeClass(updatedEmployee.getEmployeeClass());
		if (updatedEmployee.getCompanyName() != null) existingEmployee.setCompanyName(updatedEmployee.getCompanyName());
		if (updatedEmployee.getContractEndDate() != null) existingEmployee.setContractEndDate(updatedEmployee.getContractEndDate());
		if (updatedEmployee.getCountryName() != null) existingEmployee.setCountryName(updatedEmployee.getCountryName());
		if (updatedEmployee.getDepartment() != null) existingEmployee.setDepartment(updatedEmployee.getDepartment());
		if (updatedEmployee.getDepartmentId() != null) existingEmployee.setDepartmentId(updatedEmployee.getDepartmentId());
		if (updatedEmployee.getDivisionId() != null) existingEmployee.setDivisionId(updatedEmployee.getDivisionId());
		if (updatedEmployee.getJobCode() != null) existingEmployee.setJobCode(updatedEmployee.getJobCode());
		if (updatedEmployee.getJobTitle() != null) existingEmployee.setJobTitle(updatedEmployee.getJobTitle());
		if (updatedEmployee.getLocation() != null) existingEmployee.setLocation(updatedEmployee.getLocation());
		if (updatedEmployee.getManagerId() != null) existingEmployee.setManagerId(updatedEmployee.getManagerId());
		if (updatedEmployee.getPostalCode() != null) existingEmployee.setPostalCode(updatedEmployee.getPostalCode());
		if (updatedEmployee.getEmail() != null) existingEmployee.setEmail(updatedEmployee.getEmail());
		if (updatedEmployee.getDivision() != null) existingEmployee.setDivision(updatedEmployee.getDivision());
		if (updatedEmployee.getWorkforceType() != null) existingEmployee.setWorkforceType(updatedEmployee.getWorkforceType());
		if(updatedEmployee.getWorkerStatus() != null) existingEmployee.setWorkerStatus(updatedEmployee.getWorkerStatus());
		// Not updating: employeeNumber, employeeType, fileNumber, userId, workerName, hireDate, firstName, lastName

		return employeeRepository.save(existingEmployee);
	}
}

