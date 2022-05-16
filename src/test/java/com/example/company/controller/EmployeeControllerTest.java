package com.example.company.controller;

import com.example.company.EmployeeData;
import com.example.company.dto.request.EmployeeRequest;
import com.example.company.facade.EmployeeFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeFacade employeeFacade;

    private static final String URL = "/employee";

    private EmployeeData data;

    @BeforeEach
    private void setup() {data = new EmployeeData();};

    @Test
    public void returnSucessWhen_findEmployee() throws Exception {

        String urlFIndEmployee = URL+"/employeeId/1";

        mockMvc.perform(MockMvcRequestBuilders.get(urlFIndEmployee)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void returnSucessWhen_findEmployeesByParameter() throws Exception {

        String urlFIndEmployee = URL+"/parameterType/employeeId/parameterValue/1/page/0/limit/3";

        mockMvc.perform(MockMvcRequestBuilders.get(urlFIndEmployee)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void returnSucessWhen_saveEmployee() throws Exception {

        EmployeeRequest employeeRequest = data.createEmployeeRequest();

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJson(employeeRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void returnSucessWhen_updateEmployee() throws Exception {

        EmployeeRequest employeeRequest = data.createEmployeeRequest();

        mockMvc.perform(MockMvcRequestBuilders.put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJson(employeeRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void returnSucessWhen_deleteEmployee() throws Exception {
        String urlFIndEmployee = URL+"/employeeId/1";

        mockMvc.perform(MockMvcRequestBuilders.delete(urlFIndEmployee)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String toJson(Object o){
        try {
            String json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(o);
            return json;
        } catch (JsonProcessingException e){
            return "";
        }
    }
}
