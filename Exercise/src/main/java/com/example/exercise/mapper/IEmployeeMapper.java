package com.example.exercise.mapper;


import com.example.exercise.dto.employee.EmployeeRequest;
import com.example.exercise.dto.employee.EmployeeResponse;
import com.example.exercise.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEmployeeMapper {
    Employee employeeReEmployeeToEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse employeeEmployeeToEmployeeResponse(Employee employee);
}
