package com.sailpoint.projectUtils;

import com.sailpoint.workday.entity.Employee;
import com.sailpoint.workday.repository.EmployeeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.CSVPrinter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

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

    public String exportCSV(Path filePath) {
        try (Writer writer = new FileWriter(filePath.toFile());
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withDelimiter(',')
                     .withQuote('"')
                     .withHeader(
                             "employee_number",
                             "filenumber",
                             "first_name",
                             "last_name",
                             "worker_name",
                             "email",
                             "class",
                             "employee_type",
                             "worker_status",
                             "workforce_type",
                             "company_name",
                             "contract_end_date",
                             "department",
                             "department_id",
                             "division",
                             "division_id",
                             "business_unit_id",
                             "jobcode",
                             "jobtitle",
                             "hiredate",
                             "manager_id",
                             "address_line_1",
                             "address_work",
                             "building_code",
                             "postal_code",
                             "region",
                             "location",
                             "country_name"
                     )
             )) {

            List<Employee> employees = employeeRepository.findAll();

            for (Employee emp : employees) {
                csvPrinter.printRecord(
                        emp.getEmployeeNumber(),
                        emp.getFileNumber(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getWorkerName(),
                        emp.getEmail(),
                        emp.getEmployeeClass(),
                        emp.getEmployeeType(),
                        emp.getWorkerStatus(),
                        emp.getWorkforceType(),
                        emp.getCompanyName(),
                        emp.getContractEndDate(),
                        emp.getDepartment(),
                        emp.getDepartmentId(),
                        emp.getDivision(),
                        emp.getDivisionId(),
                        emp.getBusinessUnitId(),
                        emp.getJobCode(),
                        emp.getJobTitle(),
                        emp.getHireDate(),
                        emp.getManagerId(),
                        emp.getAddressLine1(),
                        emp.getAddressWork(),
                        emp.getBuildingCode(),
                        emp.getPostalCode(),
                        emp.getRegion(),
                        emp.getLocation(),
                        emp.getCountryName()
                );
            }

            csvPrinter.flush();
            return "export success";

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
