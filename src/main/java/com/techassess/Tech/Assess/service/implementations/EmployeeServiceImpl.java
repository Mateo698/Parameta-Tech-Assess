package com.techassess.Tech.Assess.service.implementations;

import com.techassess.Tech.Assess.dto.EmployeeInDTO;
import com.techassess.Tech.Assess.dto.EmployeeOutDTO;
import com.techassess.Tech.Assess.soap.EmployeeRequest;
import com.techassess.Tech.Assess.soap.EmployeeResponse;
import com.techassess.Tech.Assess.error.EmployeeException;
import com.techassess.Tech.Assess.mapper.EmployeeMapper;
import com.techassess.Tech.Assess.model.DateDifference;
import com.techassess.Tech.Assess.model.Employee;
import com.techassess.Tech.Assess.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final WebServiceTemplate webServiceTemplate;


    @Override
    public EmployeeOutDTO addEmployee(EmployeeInDTO employeeOutDTO) {
        validateEmployeeFields(employeeOutDTO);
        LocalDate birthDate = validateDate(employeeOutDTO.getBirthDate(), "birthDate");
        LocalDate vinculationDate = validateDate(employeeOutDTO.getVinculationDate(), "vinculationDate");

        DateDifference age = new DateDifference(birthDate,LocalDate.now());
        if(age.getYears() < 18) {
            throw new EmployeeException(
                "The employee must be at least 18 years old.",
                HttpStatus.BAD_REQUEST
            );
        }

        Employee newEmployee = employeeMapper.fromEmployeeInDTO(employeeOutDTO);

        //Call SAOP service to save to MySQL database
        EmployeeRequest employeeRequest = employeeMapper.fromEmployeetoEmployeeRequest(newEmployee);
        EmployeeResponse employeeResponse = (EmployeeResponse) webServiceTemplate.marshalSendAndReceive(
            "http://localhost:8080/ws",
            employeeRequest
        );

        if (!employeeResponse.getMessage().equalsIgnoreCase("Employee saved successfully!")) {
            throw new EmployeeException("Failed to save employee via SOAP service.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //

        DateDifference timeSinceVinculation = new DateDifference(vinculationDate, LocalDate.now());

        EmployeeOutDTO employeeOut = employeeMapper.fromEmployee(newEmployee);
        employeeOut.setAge(age.toString());
        employeeOut.setTimeSinceVinculation(timeSinceVinculation.toString());

        return employeeOut;
    }

    private LocalDate validateDate(String dateString, String field){
        if (dateString == null || dateString.isEmpty()) {
            throw new EmployeeException(
                String.format("The field '%s' cannot be null or empty.", field),
                HttpStatus.BAD_REQUEST
            );
        }

        try {
            return LocalDate.parse(dateString);
        } catch (Exception e) {
            throw new EmployeeException(
                String.format("The field '%s' must be a valid date in the format 'yyyy-MM-dd'.", field),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    private void validateEmployeeFields(EmployeeInDTO employeeInDTO) {
        if (employeeInDTO.getFirstName() == null || employeeInDTO.getFirstName().isEmpty()) {
            throw new EmployeeException("The field 'firstName' cannot be null or empty.", HttpStatus.BAD_REQUEST);
        }
        if (employeeInDTO.getLastName() == null || employeeInDTO.getLastName().isEmpty()) {
            throw new EmployeeException("The field 'lastName' cannot be null or empty.", HttpStatus.BAD_REQUEST);
        }
        if (employeeInDTO.getDocumentType() == null || employeeInDTO.getDocumentType().isEmpty()) {
            throw new EmployeeException("The field 'documentType' cannot be null or empty.", HttpStatus.BAD_REQUEST);
        }
        if (employeeInDTO.getDocumentNumber() == null || employeeInDTO.getDocumentNumber().isEmpty()) {
            throw new EmployeeException("The field 'documentNumber' cannot be null or empty.", HttpStatus.BAD_REQUEST);
        }
        if (employeeInDTO.getPosition() == null || employeeInDTO.getPosition().isEmpty()) {
            throw new EmployeeException("The field 'position' cannot be null or empty.", HttpStatus.BAD_REQUEST);
        }
        if (employeeInDTO.getSalary() == null || employeeInDTO.getSalary() <= 0) {
            throw new EmployeeException("The field 'salary' must be a positive number.", HttpStatus.BAD_REQUEST);
        }
    }

}
