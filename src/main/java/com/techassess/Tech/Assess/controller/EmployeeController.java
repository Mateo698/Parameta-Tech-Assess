package com.techassess.Tech.Assess.controller;

import com.techassess.Tech.Assess.api.EmployeeAPI;
import com.techassess.Tech.Assess.dto.EmployeeInDTO;
import com.techassess.Tech.Assess.dto.EmployeeOutDTO;
import com.techassess.Tech.Assess.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmployeeController implements EmployeeAPI {

    private final EmployeeService employeeService;

    @Override
    public EmployeeOutDTO addEmployee(EmployeeInDTO employeeInDTO) {
        return employeeService.addEmployee(employeeInDTO);
    }


}
