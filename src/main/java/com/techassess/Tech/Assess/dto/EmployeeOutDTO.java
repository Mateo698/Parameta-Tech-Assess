package com.techassess.Tech.Assess.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeOutDTO {
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String birthDate; // Consider using LocalDate for better date handling
    private String vinculationDate; // Consider using LocalDate for better date handling
    private String position;
    private Double salary;
    private String timeSinceVinculation; // Calculated field for time since vinculation
    private String age; // Calculated field for age
}
