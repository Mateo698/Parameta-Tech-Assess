package com.techassess.Tech.Assess.mapper.implementations;

import com.techassess.Tech.Assess.dto.EmployeeInDTO;
import com.techassess.Tech.Assess.dto.EmployeeOutDTO;
import com.techassess.Tech.Assess.soap.EmployeeRequest;
import com.techassess.Tech.Assess.mapper.EmployeeMapper;
import com.techassess.Tech.Assess.model.Employee;

import java.time.LocalDate;

public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee fromEmployeeInDTO(EmployeeInDTO employeeInDTO) {
        return Employee.builder().firstName(employeeInDTO.getFirstName())
                       .lastName(employeeInDTO.getLastName())
                       .documentType(employeeInDTO.getDocumentType())
                       .documentNumber(employeeInDTO.getDocumentNumber())
                       .birthDate(LocalDate.parse(employeeInDTO.getBirthDate()))
                       .vinculationDate(LocalDate.parse(employeeInDTO.getVinculationDate()))
                       .position(employeeInDTO.getPosition())
                       .salary(employeeInDTO.getSalary())
                       .build();
    }

    @Override
    public EmployeeOutDTO fromEmployee(Employee employee) {
        return EmployeeOutDTO.builder().firstName(employee.getFirstName())
                             .lastName(employee.getLastName())
                             .documentType(employee.getDocumentType())
                             .documentNumber(employee.getDocumentNumber())
                             .birthDate(employee.getBirthDate().toString()) // Convert LocalDate to String
                             .vinculationDate(employee.getVinculationDate().toString()) // Convert LocalDate to String
                             .position(employee.getPosition())
                             .salary(employee.getSalary())
                             .build();
    }

    @Override
    public EmployeeRequest fromEmployeetoEmployeeRequest(Employee employee) {
        EmployeeRequest res = new EmployeeRequest();
        res.setFirstName(employee.getFirstName());
        res.setLastName(employee.getLastName());
        res.setDocumentType(employee.getDocumentType());
        res.setDocumentNumber(employee.getDocumentNumber());
        res.setPosition(employee.getPosition());
        res.setSalary(employee.getSalary());
        res.setBirthDate(employee.getBirthDate().toString()); // Convert LocalDate to String
        res.setVinculationDate(employee.getVinculationDate().toString()); // Convert LocalDate to String
        return res;
    }

}
