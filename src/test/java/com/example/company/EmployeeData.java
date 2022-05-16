package com.example.company;

import com.example.company.dto.request.EmployeeRequest;
import com.example.company.dto.response.DepartamentResponse;
import com.example.company.dto.response.EmployeeResponse;
import com.example.company.entity.Departament;
import com.example.company.entity.Employee;

import java.util.Date;

public class EmployeeData {

    public EmployeeResponse createEmployeeResponse(){
        EmployeeResponse response = new EmployeeResponse();

        response.setEmployeeId(1L);
        response.setFirstName("FIRST NAME");
        response.setMiddleName("MIDDLE NAME");
        response.setFirstLastName("FIRST LAST NAME");
        response.setSecondLastName("SECOND LAST NAME");
        response.setEmail("test@test.com");
        response.setJobCountry("ESTADOS UNIDOS");
        response.setDocumentType("PASSPORT");
        response.setDocumentNumber("123456-X");
        response.setEntryDate(new Date());
        response.setStatus("ACTIVE");
        response.setInsertDate(new Date());
        response.setDepartament(createDepartamentResponse());

        return response;
    }

    public DepartamentResponse createDepartamentResponse(){
        DepartamentResponse response = new DepartamentResponse();

        response.setDepartamentId(1L);
        response.setName("TEST DEPARTAMENT");

        return response;
    }

    public Departament createDepartament(){
        Departament departament = new Departament();
        departament.setDepartamentId(1L);
        departament.setName("FINANCES");

        return departament;
    }

    public EmployeeRequest createEmployeeRequest(){
        EmployeeRequest request = new EmployeeRequest();

        request.setEmployeeId(1L);
        request.setFirstName("FIRST NAME");
        request.setMiddleName("MIDDLE NAME");
        request.setFirstLastName("FIRST LAST NAME");
        request.setSecondLastName("SECOND LAST NAME");
        request.setJobCountry("ESTADOS UNIDOS");
        request.setDocumentType("PASSPORT");
        request.setDocumentNumber("123456-X");
        request.setEntryDate(new Date());
        request.setDepartamentId(1L);

        return request;
    }

    public Employee createEmployee(){
        Employee employee = new Employee();

        employee.setEmployeeId(1L);
        employee.setFirstName("FIRST NAME");
        employee.setMiddleName("MIDDLE NAME");
        employee.setFirstLastName("FIRST LAST NAME");
        employee.setSecondLastName("SECOND LAST NAME");
        employee.setEmail("test@test.com");
        employee.setJobCountry("ESTADOS UNIDOS");
        employee.setDocumentType("PASSPORT");
        employee.setDocumentNumber("123456-X");
        employee.setEntryDate(new Date());
        employee.setDepartament(createDepartament());

        return employee;
    }


}
