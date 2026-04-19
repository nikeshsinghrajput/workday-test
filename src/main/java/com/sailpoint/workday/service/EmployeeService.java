package com.sailpoint.workday.service;

import java.util.Optional;

import com.sailpoint.workday.dto.EmployeeGlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sailpoint.workday.entity.Employee;
import com.sailpoint.workday.entity.PageableResponse;
import com.sailpoint.workday.repository.EmployeeRepository;

import com.sailpoint.projectUtils.Helper;

import org.springframework.data.domain.Pageable;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public EmployeeGlobalResponse addEmployee(Employee employee) {
		try {
			String employeeNumber=employee.getEmployeeNumber();
			if(employeeNumber==null || employeeNumber.isEmpty()) {
				employeeNumber=getNextEmployeeNumber();
			}
			employee.setEmployeeNumber(employeeNumber);
			employee.setFileNumber(employeeNumber);
			employeeRepository.save(employee);
			return new EmployeeGlobalResponse("data added success fully", 200, employeeNumber);
		}catch (Exception e) {
			System.out.println("connection issue while adding data");
			return new EmployeeGlobalResponse("connection issue", 400, null);
		}

	}

	public String getNextEmployeeNumber() {

		Long maxEmpNo = employeeRepository.findMaxEmployeeNumber();
		Long nextEmpNo = maxEmpNo + 1;

		return String.valueOf(nextEmpNo);
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

