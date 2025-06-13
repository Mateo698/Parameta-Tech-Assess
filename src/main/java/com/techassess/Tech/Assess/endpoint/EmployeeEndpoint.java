package com.techassess.Tech.Assess.endpoint;

import com.techassess.Tech.Assess.soap.EmployeeRequest;
import com.techassess.Tech.Assess.soap.EmployeeResponse;
import com.techassess.Tech.Assess.model.Employee;
import com.techassess.Tech.Assess.repository.EmployeeRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDate;

@Endpoint
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://techassess.com/employeesoap";

    private final EmployeeRepository employeeRepository;

    public EmployeeEndpoint(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EmployeeRequest")
    @ResponsePayload
    public EmployeeResponse saveEmployee(@RequestPayload EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setDocumentType(request.getDocumentType());
        employee.setDocumentNumber(request.getDocumentNumber());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());
        employee.setBirthDate(LocalDate.parse(request.getBirthDate()));
        employee.setVinculationDate(LocalDate.parse(request.getVinculationDate()));

        employeeRepository.save(employee);

        EmployeeResponse response = new EmployeeResponse();
        response.setMessage("Employee saved successfully!");
        return response;
    }
}
