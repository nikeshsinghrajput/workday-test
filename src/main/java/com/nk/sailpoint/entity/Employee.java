package com.nk.sailpoint.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
	private String addressLine1;
    private String addressWork;
    private String buildingCode;
    private String businessUnitId;
    private String region;
    private String employeeClass;
    private String companyName;
    private String contractEndDate;
    private String countryName;
    private String department;
    private String departmentId;
    private String divisionId;
    private String fileNumber;
    private String firstName;
    private String hireDate;
    private String hireStatus;
    private String hireRescinded;
    private String jobCode;
    private String jobTitle;
    private String lastName;
    private String location;
    private String managerId;
    private String postalCode;
    private String rehireStatus;
    private String termStatus;
    private String userId;
    private String workerName;
    private String workforceType;
    @Id
    private String employeeNumber;
    private String email;
    private String division;
    private String employeeType;
    private String type;

    // Getters and Setters
    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }

    public String getAddressWork() { return addressWork; }
    public void setAddressWork(String addressWork) { this.addressWork = addressWork; }

    public String getBuildingCode() { return buildingCode; }
    public void setBuildingCode(String buildingCode) { this.buildingCode = buildingCode; }

    public String getBusinessUnitId() { return businessUnitId; }
    public void setBusinessUnitId(String businessUnitId) { this.businessUnitId = businessUnitId; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getEmployeeClass() { return employeeClass; }
    public void setEmployeeClass(String employeeClass) { this.employeeClass = employeeClass; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getContractEndDate() { return contractEndDate; }
    public void setContractEndDate(String contractEndDate) { this.contractEndDate = contractEndDate; }

    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getDivisionId() { return divisionId; }
    public void setDivisionId(String divisionId) { this.divisionId = divisionId; }

    public String getFileNumber() { return fileNumber; }
    public void setFileNumber(String fileNumber) { this.fileNumber = fileNumber; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }

    public String getHireStatus() { return hireStatus; }
    public void setHireStatus(String hireStatus) { this.hireStatus = hireStatus; }

    public String getHireRescinded() { return hireRescinded; }
    public void setHireRescinded(String hireRescinded) { this.hireRescinded = hireRescinded; }

    public String getJobCode() { return jobCode; }
    public void setJobCode(String jobCode) { this.jobCode = jobCode; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getManagerId() { return managerId; }
    public void setManagerId(String managerId) { this.managerId = managerId; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getRehireStatus() { return rehireStatus; }
    public void setRehireStatus(String rehireStatus) { this.rehireStatus = rehireStatus; }

    public String getTermStatus() { return termStatus; }
    public void setTermStatus(String termStatus) { this.termStatus = termStatus; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getWorkerName() { return workerName; }
    public void setWorkerName(String workerName) { this.workerName = workerName; }

    public String getWorkforceType() { return workforceType; }
    public void setWorkforceType(String workforceType) { this.workforceType = workforceType; }

    public String getEmployeeNumber() { return employeeNumber; }
    public void setEmployeeNumber(String employeeNumber) { this.employeeNumber = employeeNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public String getEmployeeType() { return employeeType; }
    public void setEmployeeType(String employeeType) { this.employeeType = employeeType; }
}
