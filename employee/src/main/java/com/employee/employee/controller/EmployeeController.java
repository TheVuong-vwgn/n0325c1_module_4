package com.employee.employee.controller;

import com.employee.employee.dto.JsonResponse;
import com.employee.employee.exception.AppException;
import com.employee.employee.exception.ErrorCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(UUID.randomUUID(), "Hoàng Văn Hải", LocalDate.of(1990, 1, 15),
                            Gender.MALE, 15000000.00, "0975125424",1),
                    new Employee(UUID.randomUUID(), "Trần Thị Hoài", LocalDate.of(1985, 5, 20),
                            Gender.FEMALE, 14500000.00, "0967896848",2),
                    new Employee(UUID.randomUUID(), "Lê Văn Sỹ", LocalDate.of(1992, 3, 10),
                            Gender.MALE, 15500000.00, "0988811100",3),
                    new Employee(UUID.randomUUID(), "Phạm Duy Khánh", LocalDate.of(1988, 7, 9),
                            Gender.MALE, 14000000.00, "0964555333", 4),
                    new Employee(UUID.randomUUID(), "Hoàng Văn Quý", LocalDate.of(1995, 9, 25),
                            Gender.MALE, 15200000.00, "0971388648", 5)
            )
    );

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dobFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(value = "dobTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(value = "gender", required = false) Gender gender,
            @RequestParam(value = "salaryRange", required = false) String salaryRange,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "departmentId", required = false) Integer departmentId
    ) {
        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> (name == null || e.getName().toLowerCase().contains(name.toLowerCase())))
                .filter(e -> (dobFrom == null || !e.getDob().isBefore(dobFrom)))
                .filter(e -> (dobTo == null || !e.getDob().isAfter(dobTo)))
                .filter(e -> (gender == null || e.getGender() == gender))
                .filter(e -> (phone == null || e.getPhone().contains(phone)))
                .filter(e -> (departmentId == null || Objects.equals(e.getDepartmentId(), departmentId)))
                .filter(e -> {
                    if (salaryRange == null) {
                        return true;
                    }
                    return switch (salaryRange) {
                        case "lt5" ->
                                e.getSalary() < 5000000;
                        case "5-10" ->
                                e.getSalary() >= 5000000 && e.getSalary() < 10000000;
                        case "10-20" ->
                                e.getSalary() >= 10000000 && e.getSalary() <= 20000000;
                        case "gt20" ->
                                e.getSalary() > 20000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());
        return JsonResponse.ok(filteredEmployees);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getById(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(JsonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return JsonResponse.created(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable("id") UUID id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setDob(employee.getDob());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());

                    return JsonResponse.ok(e);
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e-> e.getId().equals(id))
                .findFirst()
                .map(s -> {
                    employees.remove(s);
                    return JsonResponse.noContent();
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED));
    }
}