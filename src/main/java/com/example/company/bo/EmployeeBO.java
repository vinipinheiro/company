package com.example.company.bo;

import com.example.company.dto.request.EmployeeRequest;
import com.example.company.dto.response.DepartamentResponse;
import com.example.company.dto.response.EmployeeResponse;
import com.example.company.entity.Departament;
import com.example.company.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeBO {

    public EmployeeResponse convertResponseFromEntity(Employee employee){

            EmployeeResponse employeeResponse = new EmployeeResponse();

            employeeResponse.setEmployeeId(employee.getEmployeeId());
            employeeResponse.setEmail(employee.getEmail());
            employeeResponse.setFirstName(employee.getFirstName());
            employeeResponse.setMiddleName(employee.getMiddleName());
            employeeResponse.setFirstLastName(employee.getFirstLastName());
            employeeResponse.setSecondLastName(employee.getSecondLastName());
            employeeResponse.setJobCountry(employee.getJobCountry());
            employeeResponse.setDocumentType(employee.getDocumentType());
            employeeResponse.setDocumentNumber(employee.getDocumentNumber());
            employeeResponse.setEntryDate(employee.getEntryDate());
            employeeResponse.setStatus(employee.getStatus());
            employeeResponse.setInsertDate(employee.getInsertDate());
            DepartamentResponse departamentResponse = new DepartamentResponse();
            departamentResponse.setDepartamentId(employee.getDepartament().getDepartamentId());
            departamentResponse.setName(employee.getDepartament().getName());
            employeeResponse.setDepartament(departamentResponse);

            return employeeResponse;
    }

    public List<EmployeeResponse> convertListResponseFromListEntity(List<Employee> employees){
        List<EmployeeResponse> employeeResponseList = Optional.ofNullable(employees).orElseGet(Collections::emptyList).stream().map(
                employee -> {
                    EmployeeResponse employeeResponse = convertResponseFromEntity(employee);

                    return employeeResponse;
                }).collect(Collectors.toList());
        return employeeResponseList;
    }

    public Employee convertEntityFromRequest(EmployeeRequest request){

        Employee employee = new Employee();

        employee.setEmployeeId(request.getEmployeeId());
        employee.setFirstName(request.getFirstName().toUpperCase().trim());
        employee.setMiddleName(request.getMiddleName().toUpperCase().trim());
        employee.setFirstLastName(request.getFirstLastName().toUpperCase().trim());
        employee.setSecondLastName(request.getSecondLastName().toUpperCase().trim());
        employee.setJobCountry(request.getJobCountry().toUpperCase().trim());
        employee.setDocumentType(request.getDocumentType().toUpperCase().trim());
        employee.setDocumentNumber(request.getDocumentNumber().toUpperCase().trim());
        employee.setEntryDate(request.getEntryDate());
        Departament departament = new Departament();
        departament.setDepartamentId(request.getDepartamentId());
        employee.setDepartament(departament);

        return employee;
    }
}
