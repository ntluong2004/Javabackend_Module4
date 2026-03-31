package com.tlu.learning.ex_full.controller;

import com.tlu.learning.ex_full.dto.ApiResponse;
import com.tlu.learning.ex_full.dto.EmployeeSearchRequest;
import com.tlu.learning.ex_full.model.Employee;
import com.tlu.learning.ex_full.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("employeeController")
@RequestMapping("ex_employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployeeService employeeService;

    @GetMapping
    public ApiResponse<List<Employee>> search(EmployeeSearchRequest request) {
        return ApiResponse.<List<Employee>>builder()
                .code(1000)
                .result(employeeService.search(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Employee> getById(@PathVariable String id) {
        return ApiResponse.<Employee>builder()
                .code(1000)
                .result(employeeService.getById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<Employee> create(@RequestBody Employee employee) {
        return ApiResponse.<Employee>builder()
                .code(1000)
                .message("Employee created")
                .result(employeeService.create(employee))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Employee> update(@PathVariable String id, @RequestBody Employee employee) {
        return ApiResponse.<Employee>builder()
                .code(1000)
                .message("Employee updated")
                .result(employeeService.update(id, employee))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable String id) {
        employeeService.delete(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Employee deleted successfully")
                .build();
    }
}