package com.tlu.learning.ex_full.repository.impl;

import com.tlu.learning.ex_full.dto.EmployeeSearchRequest;
import com.tlu.learning.ex_full.model.Employee;
import com.tlu.learning.ex_full.repository.IEmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryImpl implements IEmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> search(EmployeeSearchRequest request) {
        return employees.stream()
                .filter(e -> request.getName() == null || e.getName().toLowerCase().contains(request.getName().toLowerCase()))
                .filter(e -> request.getGender() == null || e.getGender().toString().equalsIgnoreCase(request.getGender()))
                .filter(e -> request.getDepartmentId() == null || e.getDepartmentId().equals(request.getDepartmentId()))
                .filter(e -> filterBySalary(e.getSalary(), request.getSalaryRange()))
                .collect(Collectors.toList());
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

    @Override
    public Optional<Employee> findById(String id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Employee save(Employee employee) {
        employees.removeIf(e -> e.getId().equals(employee.getId()));
        employees.add(employee);
        return employee;
    }

    @Override
    public boolean deleteById(String id) {
        return employees.removeIf(e -> e.getId().equals(id));
    }
}