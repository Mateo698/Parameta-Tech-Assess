package com.techassess.Tech.Assess.unit;

import com.techassess.Tech.Assess.dto.EmployeeInDTO;
import com.techassess.Tech.Assess.dto.EmployeeOutDTO;
import com.techassess.Tech.Assess.error.EmployeeException;
import com.techassess.Tech.Assess.mapper.EmployeeMapper;
import com.techassess.Tech.Assess.model.Employee;
import com.techassess.Tech.Assess.service.EmployeeService;
import com.techassess.Tech.Assess.service.implementations.EmployeeServiceImpl;
import com.techassess.Tech.Assess.soap.EmployeeRequest;
import com.techassess.Tech.Assess.soap.EmployeeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    WebServiceTemplate webServiceTemplate;

    @Mock
    EmployeeMapper employeeMapper;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    void addEmployee_HappyPath_ReturnsEmployeeOutDTO() {
        // Given
        EmployeeInDTO input = EmployeeInDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345")
                .position("Engineer")
                .salary(5000.0)
                .birthDate(LocalDate.now().minusYears(25).toString())
                .vinculationDate(LocalDate.now().minusYears(1).toString())
                .build();

        Employee mockEmployee = new Employee();
        EmployeeRequest mockRequest = new EmployeeRequest();
        EmployeeResponse mockResponse = new EmployeeResponse();
        mockResponse.setMessage("Employee saved successfully!");
        EmployeeOutDTO expectedOut = new EmployeeOutDTO();

        Mockito.when(employeeMapper.fromEmployeeInDTO(input)).thenReturn(mockEmployee);
        Mockito.when(employeeMapper.fromEmployeetoEmployeeRequest(mockEmployee)).thenReturn(mockRequest);
        Mockito.when(webServiceTemplate.marshalSendAndReceive("http://localhost:8080/ws", mockRequest))
                .thenReturn(mockResponse);
        Mockito.when(employeeMapper.fromEmployee(mockEmployee)).thenReturn(expectedOut);

        // When
        EmployeeOutDTO result = employeeService.addEmployee(input);

        // Then
        Assertions.assertNotNull(result);
    }

    @Test
    void addEmployee_MissingPosition_ThrowsException() {
        // Position is empty
        EmployeeInDTO input = EmployeeInDTO.builder()
                .firstName("Joe")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345")
                .salary(5000.0)
                .birthDate(LocalDate.now().minusYears(25).toString())
                .vinculationDate(LocalDate.now().minusYears(1).toString())
                .build();

        EmployeeException exception = Assertions.assertThrows(EmployeeException.class, () -> {
            employeeService.addEmployee(input);
        });

        Assertions.assertEquals("The field 'position' cannot be null or empty.", exception.getMessage());
    }

    @Test
    void addEmployee_InvalidDateFormat_ThrowsException() {
        EmployeeInDTO input = EmployeeInDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345")
                .position("Engineer")
                .salary(5000.0)
                .birthDate("invalid-date")
                .vinculationDate("2023-01-01")
                .build();

        EmployeeException exception = Assertions.assertThrows(EmployeeException.class, () -> {
            employeeService.addEmployee(input);
        });

        Assertions.assertTrue(exception.getMessage().contains("must be a valid date"));
    }

    @Test
    void addEmployee_Under18_ThrowsException() {
        EmployeeInDTO input = EmployeeInDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345").
                position("Engineer")
                .salary(5000.0)
                .birthDate("2023-01-01")
                .vinculationDate("2023-10-01") // This date is in the future, making the employee under 18
                .build();

        EmployeeException exception = Assertions.assertThrows(EmployeeException.class, () -> {
            employeeService.addEmployee(input);
        });

        Assertions.assertEquals("The employee must be at least 18 years old.", exception.getMessage());
    }


}
