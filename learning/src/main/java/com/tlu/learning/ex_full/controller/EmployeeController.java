package com.tlu.learning.ex_full.controller;

import com.tlu.learning.ex_full.Employee;
import com.tlu.learning.ex_full.Gender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(Employee.builder()
                .id(UUID.randomUUID().toString())
                .name("Nguyen Tien Luong")
                .dob(LocalDate.of(2004, 3, 16))
                .gender(Gender.MALE)
                .salary(1500.0)
                .phone("0905123456").build());
    }

    @GetMapping
    public List<Employee> getAll() {
        return employees;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(String id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable String id, @RequestBody Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                updatedEmployee.setId(id);
                employees.set(i, updatedEmployee);
                return ResponseEntity.ok(updatedEmployee);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean removed = employees.removeIf(e -> e.getId().equals(id));
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
