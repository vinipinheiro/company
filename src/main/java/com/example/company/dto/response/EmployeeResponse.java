package com.example.company.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeResponse {

    @ApiModelProperty(example = "1", value = "Employee ID", dataType = "Long", required = true)
    private Long employeeId;
    @ApiModelProperty(example = "VINICIUS", value = "Employee Fist Name", dataType = "String", required = true)
    private String firstName;
    @ApiModelProperty(example = "SILVA", value = "Employee Other Name", dataType = "String", required = false)
    private String middleName;
    @ApiModelProperty(example = "SEBASTIAO", value = "Employee Middle Name", dataType = "String", required = true)
    private String firstLastName;
    @ApiModelProperty(example = "PINHEIRO", value = "Employee Middle Name", dataType = "String", required = true)
    private String secondLastName;
    @ApiModelProperty(example = "vinicius.pinheiro@cidenet.com.co", value = "Employee e-mail", dataType = "String", required = true)
    private String email;
    @ApiModelProperty(example = "ESTADOS UNIDOS", value = "Country Job", dataType = "String", required = true)
    private String jobCountry;
    @ApiModelProperty(example = "PASSPORT", value = "Type Employee Document", dataType = "String", required = true)
    private String documentType;
    @ApiModelProperty(example = "123456-X", value = "Number Employee Document", dataType = "String", required = true)
    private String documentNumber;
    @JsonFormat(pattern = "MM-dd-yyyy")
    @ApiModelProperty(example = "", value = "Employee Entry Date", dataType = "LocalDateTime", required = true)
    private Date entryDate;
    @ApiModelProperty(example = "Active", value = "Employee Status", dataType = "String", required = true)
    private String status;
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    @ApiModelProperty(example = "", value = "Insert Date", dataType = "LocalDateTime", required = true)
    private Date insertDate;
    @ApiModelProperty(example = "1 - Finances", value = "Employee Departament", dataType = "Departament", required = true)
    private DepartamentResponse departament;
}