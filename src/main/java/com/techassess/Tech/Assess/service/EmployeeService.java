package com.techassess.Tech.Assess.service;

import com.techassess.Tech.Assess.dto.EmployeeInDTO;
import com.techassess.Tech.Assess.dto.EmployeeOutDTO;

public interface EmployeeService {
    EmployeeOutDTO addEmployee(EmployeeInDTO employeeOutDTO);
}
