package com.techassess.Tech.Assess.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmployeeException extends RuntimeException{
    private final HttpStatus status;
    private final String code;

    public EmployeeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.code = "400";
    }

}

