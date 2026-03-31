package com.tlu.learning.ex_full.chapter2.chapter2_ex1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController("employeeControllerChapter2Ex1")
@RequestMapping("/chapter2/ex1/employees")
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
    public List<Employee> getAll() {
        return employees;
    }

    @PostMapping
    public Employee create(@RequestBody Employee newEmployee) {
        newEmployee.setId(UUID.randomUUID().toString());
        employees.add(newEmployee);
        return newEmployee;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        boolean removed = employees.removeIf(e -> e.getId().equals(id));
        if (removed) {
            return ResponseEntity.ok("Employee deleted successfully with ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
    }
}