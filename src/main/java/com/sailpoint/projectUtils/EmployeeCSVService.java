package com.sailpoint.projectUtils;

import com.sailpoint.workday.entity.Employee;
import com.sailpoint.workday.repository.EmployeeRepository;
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
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()      // IMPORTANT: handles EMPLOYEE_NUMBER vs employee_number
                     .withAllowMissingColumnNames()
             )) {

            for (CSVRecord record : csvParser) {

                // Your file header: employee_number
                String employeeNumber = record.get("employee_number");

                // Skip if employee already exists
                if (employeeRepository.findById(employeeNumber).isPresent()) {
                    System.out.println("Skipping existing employee: " + employeeNumber);
                    continue;
                }

                Employee emp = new Employee();

                // Your file headers (lowercase with underscores)
                emp.setEmployeeNumber(employeeNumber);
                emp.setFileNumber(record.get("filenumber"));

                emp.setFirstName(record.get("first_name"));
                emp.setLastName(record.get("last_name"));
                emp.setWorkerName(record.get("worker_name"));
                emp.setEmail(record.get("email"));

                emp.setEmployeeClass(record.get("class"));          // from "class"
                emp.setEmployeeType(record.get("employee_type"));
                emp.setWorkerStatus(record.get("worker_status"));
                emp.setWorkforceType(record.get("workforce_type"));

                emp.setCompanyName(record.get("company_name"));
                emp.setContractEndDate(record.get("contract_end_date"));

                emp.setDepartment(record.get("department"));
                emp.setDepartmentId(record.get("department_id"));

                emp.setDivision(record.get("division"));
                emp.setDivisionId(record.get("division_id"));

                emp.setBusinessUnitId(record.get("business_unit_id"));

                emp.setJobCode(record.get("jobcode"));
                emp.setJobTitle(record.get("jobtitle"));
                emp.setHireDate(record.get("hiredate"));

                emp.setManagerId(record.get("manager_id"));

                emp.setAddressLine1(record.get("address_line_1"));
                emp.setAddressWork(record.get("address_work"));
                emp.setBuildingCode(record.get("building_code"));

                emp.setPostalCode(record.get("postal_code"));
                emp.setRegion(record.get("region"));
                emp.setLocation(record.get("location"));
                emp.setCountryName(record.get("country_name"));

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
