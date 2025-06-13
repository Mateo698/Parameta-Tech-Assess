package com.techassess.Tech.Assess.mapper;
import com.techassess.Tech.Assess.dto.EmployeeInDTO;
import com.techassess.Tech.Assess.dto.EmployeeOutDTO;
import com.techassess.Tech.Assess.soap.EmployeeRequest;
import com.techassess.Tech.Assess.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee fromEmployeeInDTO(EmployeeInDTO employeeInDTO);

    EmployeeOutDTO fromEmployee(Employee employee);

    EmployeeRequest fromEmployeetoEmployeeRequest(Employee employee);
}
