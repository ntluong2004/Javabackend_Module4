package com.tlu.learning.ex_full.service;

import com.tlu.learning.ex_full.dto.EmployeeSearchRequest;
import com.tlu.learning.ex_full.model.Employee;
import java.util.List;

public interface IEmployeeService {
    List<Employee> search(EmployeeSearchRequest request);
    Employee getById(String id);
    Employee create(Employee employee);
    Employee update(String id, Employee employee);
    void delete(String id);
}