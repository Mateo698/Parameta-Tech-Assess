package com.techassess.Tech.Assess.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@AllArgsConstructor
public class EmployeeApplicationError {
    private final String code;
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;
}
