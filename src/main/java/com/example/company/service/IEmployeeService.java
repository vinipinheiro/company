package com.example.company.service;

import com.example.company.dto.response.EmployeeResponse;
import com.example.company.dto.response.PageResponse;
import com.example.company.entity.Employee;

public interface IEmployeeService {

    EmployeeResponse findEmployeeById(Long employeeId);
    PageResponse<EmployeeResponse> findEmployeesByParameter(String parameterType, String parameterValue, int page, int limit);
    EmployeeResponse saveEmployee(Employee employee);
    EmployeeResponse updateEmployee(Employee employee);
    String deleteEmployee(Long employeeId);

}
