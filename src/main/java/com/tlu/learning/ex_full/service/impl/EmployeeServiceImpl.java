package com.tlu.learning.ex_full.service.impl;

import com.tlu.learning.ex_full.dto.EmployeeSearchRequest;
import com.tlu.learning.ex_full.exception.AppException;
import com.tlu.learning.ex_full.exception.ErrorCode;
import com.tlu.learning.ex_full.model.Employee;
import com.tlu.learning.ex_full.repository.IEmployeeRepository;
import com.tlu.learning.ex_full.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> search(EmployeeSearchRequest request) {
        return employeeRepository.search(request);
    }

    @Override
    public Employee getById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(String id, Employee employee) {
        getById(id); // Kiểm tra tồn tại
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(String id) {
        if (!employeeRepository.deleteById(id)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
    }
}