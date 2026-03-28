package com.tlu.learning.ex_full.chapter2.chapter2_ex3;

import com.tlu.learning.ex_full.chapter2.chapter2_ex1.Gender;
import com.tlu.learning.ex_full.chapter2.chapter2_ex2.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController("employeeControllerChapter2Ex3")
@RequestMapping("/chapter2/ex3/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeController() {

        employees.add(Employee.builder()
                .id(UUID.randomUUID().toString())
                .name("Luong")
                .gender(Gender.MALE)
                .salary(1500)
                .departmentId("DEPT_IT")
                .build());
    }

    @GetMapping
    public ApiResponse<List<Employee>> getAll() {
        return ApiResponse.<List<Employee>>builder()
                .code(1000)
                .result(employees)
                .build();
    }

    @PostMapping
    public ApiResponse<Employee> create(@RequestBody Employee request) {
        request.setId(UUID.randomUUID().toString());
        employees.add(request);
        return ApiResponse.<Employee>builder()
                .code(1000)
                .message("Employee created successfully")
                .result(request)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<Employee>> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String salaryRange,
            @RequestParam(required = false) String departmentId
    ) {
        List<Employee> result = employees.stream()
                .filter(e -> name == null || e.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(e -> gender == null || e.getGender().toString().equalsIgnoreCase(gender))
                .filter(e -> departmentId == null || e.getDepartmentId().equals(departmentId))
                .filter(e -> filterBySalary(e.getSalary(), salaryRange))
                .collect(Collectors.toList());

        return ApiResponse.<List<Employee>>builder()
                .code(1000)
                .result(result)
                .build();
    }

    private boolean filterBySalary(double salary, String range) {
        if (range == null || range.isEmpty()) return true;
        switch (range) {
            case "lt5": return salary < 500;
            case "5-10": return salary >= 500 && salary <= 1000;
            case "10-20": return salary > 1000 && salary <= 2000;
            case "gt20": return salary > 2000;
            default: return true;
        }
    }
}