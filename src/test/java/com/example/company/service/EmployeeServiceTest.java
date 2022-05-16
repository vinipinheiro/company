package com.example.company.service;

import com.example.company.EmployeeData;
import com.example.company.bo.EmployeeBO;
import com.example.company.dto.response.EmployeeResponse;
import com.example.company.entity.Departament;
import com.example.company.entity.Employee;
import com.example.company.repository.IDepartamentRepository;
import com.example.company.repository.IEmployeeRepository;
import com.example.company.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private IEmployeeRepository employeeRepository;

    @Mock
    private IDepartamentRepository departamentRepository;

    @Mock
    private EmployeeBO employeeBO;

    private EmployeeData data;

    @BeforeEach
    private void setup() {data = new EmployeeData();}

    @Test
    public void returnSucessWhen_findEmployee(){

        Optional<Employee> employeeOptional = Optional.of(data.createEmployee());
        EmployeeResponse expected = data.createEmployeeResponse();

        when(employeeRepository.findById(1L)).thenReturn(employeeOptional);
        when(employeeBO.convertResponseFromEntity(employeeOptional.get())).thenReturn(expected);

        EmployeeResponse actual = employeeService.findEmployeeById(1L);
        assertEquals(actual, expected);
    }

    @Test
    public void returnSucessWhen_saveEmployee(){

        Employee employee = data.createEmployee();
        EmployeeResponse expected = data.createEmployeeResponse();

        Departament departament = data.createDepartament();

        when(employeeRepository.findDocument(employee.getDocumentType(), employee.getDocumentNumber())).thenReturn(Optional.empty());
        when(departamentRepository.getById(any())).thenReturn(departament);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeBO.convertResponseFromEntity(employee)).thenReturn(expected);

        EmployeeResponse actual = employeeService.saveEmployee(employee);
        assertEquals(actual, expected);
    }

    @Test
    public void returnSucessWhen_updateEmployee(){

        Employee employee = data.createEmployee();
        EmployeeResponse expected = data.createEmployeeResponse();

        Departament departament = data.createDepartament();

        when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(data.createEmployee()));
        when(departamentRepository.getById(any())).thenReturn(departament);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeBO.convertResponseFromEntity(employee)).thenReturn(expected);

        EmployeeResponse actual = employeeService.updateEmployee(employee);
        assertEquals(actual, expected);
    }

    @Test
    public void returnSucessWhen_deleteEmployee(){
        Employee employee = data.createEmployee();
        String expected = "The employee with e-mail: test@test.com has been deleted.";

        when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(data.createEmployee()));

        String actual = employeeService.deleteEmployee(employee.getEmployeeId());
        assertEquals(actual, expected);
    }
}
