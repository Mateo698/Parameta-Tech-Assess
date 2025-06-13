package com.techassess.Tech.Assess.api;

import com.techassess.Tech.Assess.dto.EmployeeInDTO;
import com.techassess.Tech.Assess.dto.EmployeeOutDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/employees")
public interface EmployeeAPI {
    //Should be POST instead of GET for adding an employee
    @GetMapping("/add")
    EmployeeOutDTO addEmployee(@RequestBody EmployeeInDTO employeeInDTO);

}
