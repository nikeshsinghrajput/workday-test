package com.workday.sailpoint.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_work")
    private String addressWork;

    @Column(name = "building_code")
    private String buildingCode;

    @Column(name = "business_unit_id")
    private String businessUnitId;

    @Column(name = "region")
    private String region;

    @Column(name = "class")
    private String employeeClass;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "contract_end_date")
    private String contractEndDate;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "department")
    private String department;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "division_id")
    private String divisionId;

    @Column(name = "filenumber")
    private String fileNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "hiredate")
    private String hireDate;

    @Column(name = "worker_status")
    private String workerStatus;

    @Column(name = "jobcode")
    private String jobCode;

    @Column(name = "jobtitle")
    private String jobTitle;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "location")
    private String location;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "worker_name")
    private String workerName;

    @Column(name = "workforce_type")
    private String workforceType;

    @Id
    @Column(name = "employee_number")
    private String employeeNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "division")
    private String division;

    @Column(name = "employee_type")
    private String employeeType;
}
