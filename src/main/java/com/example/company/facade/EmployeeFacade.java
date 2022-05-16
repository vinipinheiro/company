package com.example.company.facade;

import com.example.company.bo.EmployeeBO;
import com.example.company.dto.request.EmployeeRequest;
import com.example.company.dto.response.EmployeeResponse;
import com.example.company.dto.response.PageResponse;
import com.example.company.entity.Employee;
import com.example.company.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFacade {

    @Autowired
    public IEmployeeService employeeService;

    @Autowired
    public EmployeeBO employeeBO;

    public EmployeeResponse findEmployee(Long employeeId){

        return employeeService.findEmployeeById(employeeId);
    }

    public PageResponse<EmployeeResponse> findEmployeesByParameter(String parameterType, String parameterValue, int page, int limit) {

        return employeeService.findEmployeesByParameter(parameterType, parameterValue, page, limit);
    }

    public EmployeeResponse saveEmployee(EmployeeRequest request){

        Employee employee = employeeBO.convertEntityFromRequest(request);

        return employeeService.saveEmployee(employee);
    }

    public EmployeeResponse updateEmployee(EmployeeRequest request){

        Employee employee = employeeBO.convertEntityFromRequest(request);

        return employeeService.updateEmployee(employee);
    }

    public String deleteEmployee(Long employeeId){

        return employeeService.deleteEmployee(employeeId);
    }
}
