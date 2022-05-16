package com.example.company.commom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessExceptionEnum {

    EMPLOYEE_NOT_FOUND(0, "employee.not.found", 404),
    EMPLOYEES_NOT_FOUND(1, "employees.not.found", 404),
    DOCUMENT_EXISTS(2, "document.exists", 403),
    ENTRY_DATE_AFTER(3, "entry.date.after", 403),
    ENTRY_DATE_BEFORE(4, "entry.date.before", 403),
    EMAIL_MAX_LENGHT(5, "email.max.lenght", 403),
    DEPARTAMENT_DOESNT_EXIST(6, "departament.doesnt.exist", 403);

    private Integer code;
    private String message;
    private Integer statusCode;
}
