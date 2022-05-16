package com.example.company.commom;

import lombok.Data;

import java.io.Serializable;


@Data
public class BusinessException extends RuntimeException implements Serializable {

    private final String message;
    private final Integer code;
    private final String type;


    public BusinessException(BusinessExceptionEnum errorEnum){
       this.message = errorEnum.getMessage();
       this.code = errorEnum.getStatusCode();
       this.type = "enum";
    }

    public BusinessException(String errorMessage){
        this.message = errorMessage;
        this.code = 404;
        this.type = "manual";
    }
}
