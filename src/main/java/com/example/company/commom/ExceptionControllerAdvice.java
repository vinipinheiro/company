package com.example.company.commom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handle(BusinessException exception) {

        String message = exception.getMessage();
        if(exception.getType().equals("enum")){
            message += " : " + messageSource.getMessage(exception.getMessage(), new Object[0], Locale.US);
        }

        return ResponseEntity.status(exception.getCode()).body(message);
    }

}
