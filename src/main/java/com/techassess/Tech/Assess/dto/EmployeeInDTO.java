package com.techassess.Tech.Assess.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeInDTO {
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String birthDate;
    private String vinculationDate;
    private String position;
    private Double salary;
}
