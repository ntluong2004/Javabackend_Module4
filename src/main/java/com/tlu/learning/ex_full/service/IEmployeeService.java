package com.tlu.learning.ex_full.service;

import com.tlu.learning.ex_full.dto.EmployeeSearchRequest;
import com.tlu.learning.ex_full.model.Employee;
import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    List<Employee> search(EmployeeSearchRequest request);
    Employee getById(int id);
    Employee create(Employee employee);
    Employee update(int id, Employee employee);
    void delete(int id);
}