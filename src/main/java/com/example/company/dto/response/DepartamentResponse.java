package com.example.company.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DepartamentResponse {

    @ApiModelProperty(example = "1", value = "Departament ID", dataType = "Long", required = true)
    private Long departamentId;
    @ApiModelProperty(example = "FINANCES", value = "Departament Name", dataType = "String", required = true)
    private String name;

}