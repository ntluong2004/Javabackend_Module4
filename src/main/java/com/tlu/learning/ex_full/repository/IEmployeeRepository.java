package com.tlu.learning.ex_full.repository;

import com.tlu.learning.ex_full.dto.EmployeeSearchRequest;
import com.tlu.learning.ex_full.model.Employee;
import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository {
    List<Employee> search(EmployeeSearchRequest request);
    Optional<Employee> findById(String id);
    Employee save(Employee employee);
    boolean deleteById(String id);
}