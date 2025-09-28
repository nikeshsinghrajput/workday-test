package com.workday.sailpoint.util;

import com.workday.sailpoint.entity.Employee;
import com.workday.sailpoint.repository.EmployeeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;

@Service
public class EmployeeCSVService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String importCSV(Path filePath) {
        try (Reader reader = new FileReader(filePath.toFile());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withDelimiter(',')
                     .withQuote('"')
                     .withTrim()
                     .withFirstRecordAsHeader() // skip header row
             )) {

            for (CSVRecord record : csvParser) {
                String employeeNumber = record.get("EMPLOYEE_NUMBER");

                // Skip if employee already exists
                if (employeeRepository.findById(employeeNumber).isPresent()) {
                    System.out.println("Skipping existing employee: " + employeeNumber);
                    continue;
                }

                Employee emp = new Employee();
                emp.setAddressLine1(record.get("ADDRESS_LINE_1"));
                emp.setAddressWork(record.get("ADDRESS_WORK"));
                emp.setBuildingCode(record.get("BUILDING_CODE"));
                emp.setBusinessUnitId(record.get("BUSINESS_UNIT_ID"));
                emp.setRegion(record.get("REGION"));
                emp.setEmployeeClass(record.get("CLASS"));
                emp.setCompanyName(record.get("COMPANY_NAME"));
                emp.setContractEndDate(record.get("CONTRACT_END_DATE"));
                emp.setCountryName(record.get("COUNTRY_NAME"));
                emp.setDepartment(record.get("DEPARTMENT"));
                emp.setDepartmentId(record.get("DEPARTMENT_ID"));
                emp.setDivisionId(record.get("DIVISION_ID"));
                emp.setFileNumber(record.get("FILENUMBER"));
                emp.setFirstName(record.get("FIRST_NAME"));
                emp.setHireDate(record.get("HIREDATE"));
                emp.setHireStatus(record.get("HIRE_STATUS"));
                emp.setHireRescinded(record.get("Hire_Rescinded"));
                emp.setJobCode(record.get("JOBCODE"));
                emp.setJobTitle(record.get("JOBTITLE"));
                emp.setLastName(record.get("LAST_NAME"));
                emp.setLocation(record.get("LOCATION"));
                emp.setManagerId(record.get("MANAGER_ID"));
                emp.setPostalCode(record.get("POSTAL_CODE"));
                emp.setRehireStatus(record.get("REHIRE_STATUS"));
                emp.setTermStatus(record.get("TERM_STATUS"));
                emp.setWorkerName(record.get("WORKER_NAME"));
                emp.setWorkforceType(record.get("WORKFORCE_TYPE"));
                emp.setEmployeeNumber(employeeNumber);
                emp.setEmail(record.get("EMAIL"));
                emp.setDivision(record.get("DIVISION"));
                emp.setEmployeeType(record.get("EMPLOYEE_TYPE"));

                employeeRepository.save(emp);
                System.out.println("Imported employee: " + employeeNumber);
            }

            return "success";

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
