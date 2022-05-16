package com.example.company.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRequest {

    @ApiModelProperty(example = "1", value = "Employee ID", dataType = "Long", required = true)
    private Long employeeId;

    @ApiModelProperty(example = "VINICIUS", value = "Employee Fist Name", dataType = "String", required = true)
    private String firstName;

    @ApiModelProperty(example = "SILVA", value = "Employee Middle Name", dataType = "String", required = false)
    private String middleName;

    @ApiModelProperty(example = "SEBASTIAO", value = "Employee First Last Name", dataType = "String", required = true)
    private String firstLastName;

    @ApiModelProperty(example = "PINHEIRO", value = "Employee Second Last Name", dataType = "String", required = true)
    private String secondLastName;

    @ApiModelProperty(example = "ESTADOS UNIDOS", value = "Country Job", dataType = "String", required = true)
    private String jobCountry;

    @ApiModelProperty(example = "PASSPORT", value = "Type Employee Document", dataType = "String", required = true)
    private String documentType;

    @ApiModelProperty(example = "123456-X", value = "Number Employee Document", dataType = "String", required = true)
    private String documentNumber;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
    @ApiModelProperty(example = "", value = "Employee Entry Date", dataType = "LocalDateTime", required = true)
    private Date entryDate;

    @ApiModelProperty(example = "01", value = "Employee Departament ID", dataType = "Long", required = true)
    private Long departamentId;

}
