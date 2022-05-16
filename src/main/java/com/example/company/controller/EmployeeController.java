package com.example.company.controller;

import com.example.company.dto.request.EmployeeRequest;
import com.example.company.dto.response.EmployeeResponse;
import com.example.company.dto.response.PageResponse;
import com.example.company.facade.EmployeeFacade;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@Api(tags = "Parameters of employee registration control.",
        value = "Controller Rest for employee registration control.")
public class EmployeeController {

    @Autowired
    EmployeeFacade employeeFacade;

    @ApiOperation(value = "Find an employee", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucess"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping(value = "/employeeId/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> findEmployee(
            @ApiParam(name = "employeeId", required = true, value = "Employee ID", example = "1", type = "Long")
            @PathVariable(value = "employeeId", required = true)
                    Long employeeId
    ) {

        EmployeeResponse response = employeeFacade.findEmployee(employeeId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Find a pageable list of employees", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucess"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping(value = "/parameterType/{parameterType}/parameterValue/{parameterValue}/page/{page}/limit/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<EmployeeResponse>> findEmployeesByParameter(
            @ApiParam(name = "parameterType", required = true, value = "Parameter Type", example = "firstName", type = "String")
            @PathVariable(value = "parameterType", required = true)
                    String parameterType,

            @ApiParam(name = "parameterValue", required = true, value = "Parameter Value", type = "String")
            @PathVariable(value = "parameterValue", required = true)
                    String parameterValue,

            @ApiParam(name = "page", required = true, value = "Page to be Displayed", example = "1", type = "int")
            @PathVariable(value = "page", required = true)
                    int page,

            @ApiParam(name = "limit", required = true, value = "Limit of Records to be Displayed on Page", example = "10", type = "int")
            @PathVariable(value = "limit", required = true)
                    int limit
    ) {

        PageResponse<EmployeeResponse> response = employeeFacade.findEmployeesByParameter(parameterType, parameterValue, page, limit);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @ApiOperation(value = "Save an employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucess"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeRequest request) {

        EmployeeResponse response = employeeFacade.saveEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Update an employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucess"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeRequest request) {

        EmployeeResponse response = employeeFacade.updateEmployee(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Delete an employee", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucess"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @DeleteMapping(value = "/employeeId/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> deleteEmployee(
            @ApiParam(name = "employeeId", required = true, value = "Employee ID", example = "1", type = "Long")
            @PathVariable(value = "employeeId", required = true)
                    Long employeeId) {

        String response = employeeFacade.deleteEmployee(employeeId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}