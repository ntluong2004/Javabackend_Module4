package com.tlu.learning.ex_full.chapter2.chapter2_ex2;

import com.tlu.learning.ex_full.chapter2.chapter2_ex1.Employee;
import com.tlu.learning.ex_full.chapter2.chapter2_ex1.Gender;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController("employeeControllerChapter2Ex2")
@RequestMapping("/chapter2/ex2/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(Employee.builder()
                .id(UUID.randomUUID().toString())
                .name("Luong")
                .dob("2000-01-01")
                .gender(Gender.MALE)
                .salary(2500)
                .phone("0912345678")
                .build());
    }

    @GetMapping
    public ApiResponse<List<Employee>> getAll() {
        return ApiResponse.<List<Employee>>builder()
                .code(1000)
                .message("Success")
                .result(employees)
                .build();
    }

    @PostMapping
    public ApiResponse<Employee> create(@RequestBody Employee request) {
        request.setId(UUID.randomUUID().toString());
        employees.add(request);
        return ApiResponse.<Employee>builder()
                .code(1000)
                .message("Employee created")
                .result(request)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        boolean removed = employees.removeIf(e -> e.getId().equals(id));

        if (!removed) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        return ApiResponse.<String>builder()
                .code(1000)
                .message("Employee deleted successfully")
                .build();
    }
}