package com.example.company.facade;


import com.example.company.EmployeeData;
import com.example.company.bo.EmployeeBO;
import com.example.company.dto.response.EmployeeResponse;
import com.example.company.dto.response.PageResponse;
import com.example.company.entity.Employee;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeFacadeTest {

    @InjectMocks
    private EmployeeFacade employeeFacade;

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeBO employeeBO;

    private EmployeeData data;

    @BeforeEach
    private void setup(){data = new EmployeeData();}

    @Test
    public void returnSucessWhen_findEmployee(){

        EmployeeResponse expected = data.createEmployeeResponse();

        when(employeeService.findEmployeeById(1L)).thenReturn(expected);

        EmployeeResponse actual = employeeFacade.findEmployee(1L);
        assertEquals(actual, expected);
    }

    @Test
    public void returnSucessWhen_findEmployeeByParameter(){

        EmployeeResponse employeeResponse = data.createEmployeeResponse();
        List<EmployeeResponse> employeeResponseList = new ArrayList<EmployeeResponse>();
        employeeResponseList.add(employeeResponse);

        PageResponse<EmployeeResponse> expected = new PageResponse<EmployeeResponse>();
        expected.setContent(employeeResponseList);

        when(employeeService.findEmployeesByParameter("firstName", "TEST FIRST NAME", 0, 10)).thenReturn(expected);

        PageResponse<EmployeeResponse> actual = employeeFacade.findEmployeesByParameter("firstName", "TEST FIRST NAME", 0, 10);
        assertEquals(actual, expected);
    }

    @Test
    public void returnSucessWhen_saveEmployee(){

        Employee employee = employeeBO.convertEntityFromRequest(data.createEmployeeRequest());
        EmployeeResponse expected = data.createEmployeeResponse();

        when(employeeService.saveEmployee(employee)).thenReturn(expected);

        EmployeeResponse actual = employeeFacade.saveEmployee(data.createEmployeeRequest());
        assertEquals(actual, expected);
    }

    @Test
    public void returnSucessWhen_updateEmployee(){

        Employee employee = employeeBO.convertEntityFromRequest(data.createEmployeeRequest());
        EmployeeResponse expected = data.createEmployeeResponse();

        when(employeeService.updateEmployee(employee)).thenReturn(expected);

        EmployeeResponse actual = employeeFacade.updateEmployee(data.createEmployeeRequest());
        assertEquals(actual, expected);
    }

    @Test
    public void returnSucessWhen_deleteEmployee(){

        String expected = "The employee with e-mail: test@test.com has been deleted.";

        when(employeeService.deleteEmployee(1L)).thenReturn(expected);

        String actual = employeeFacade.deleteEmployee(1L);
        assertEquals(actual, expected);
    }
}
