package com.example.exercise.controller;

import com.example.exercise.dto.ApiResponse;
import com.example.exercise.dto.employee.EmployeeRequest;
import com.example.exercise.dto.employee.EmployeeResponse;
import com.example.exercise.dto.employee.EmployeeSearchRequest;
import com.example.exercise.dto.page.PageResponse;
import com.example.exercise.mapper.IEmployeeMapper;
import com.example.exercise.model.Employee;
import com.example.exercise.service.IEmployeeService;
import com.example.exercise.util.JsonResponse;
import com.example.exercise.exception.AppException;
import com.example.exercise.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/employees")
public class EmployeeController {
    IEmployeeService employeeService;
    IEmployeeMapper employeeMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<EmployeeResponse>>> getAll(
            EmployeeSearchRequest employeeSearchRequest, Pageable pageable) {
        return ResponseEntity.ok(
                ApiResponse.<PageResponse<EmployeeResponse>>builder()
                .data(new PageResponse<>(employeeService.findByAttributes(employeeSearchRequest, pageable)
                        .map(employeeMapper::employeeEmployeeToEmployeeResponse)))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return employeeService.findById(id)
                .map(JsonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.employeeReEmployeeToEmployee(employeeRequest);
        employee = employeeService.save(employee);
EmployeeResponse employeeResponse = employeeMapper.employeeEmployeeToEmployeeResponse(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<EmployeeResponse>builder()
                .data(employeeResponse)
                .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        employeeService.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        employee.setId(id);
        return JsonResponse.ok(employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        employeeService.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        employeeService.delete(id);
        return JsonResponse.noContent();
    }
}
