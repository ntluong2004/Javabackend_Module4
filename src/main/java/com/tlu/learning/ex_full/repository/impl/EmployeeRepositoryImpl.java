package com.tlu.learning.ex_full.repository.impl;

import com.tlu.learning.ex_full.dto.EmployeeSearchRequest;
import com.tlu.learning.ex_full.model.Employee;
import com.tlu.learning.ex_full.model.Gender;
import com.tlu.learning.ex_full.repository.IEmployeeRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements IEmployeeRepository {

    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = BaseRepository.getConnection()
                .prepareStatement("SELECT e.*, d.name AS department_name FROM employees e LEFT JOIN departments d ON e.department_id = d.id");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                list.add(mapResultSetToEmployee(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Employee> search(EmployeeSearchRequest request) {
        List<Employee> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = BaseRepository.getConnection()
                .prepareStatement("SELECT * FROM employees WHERE name LIKE ?")) {

            String searchName = (request.getName() != null) ? "%" + request.getName() + "%" : "%%";
            preparedStatement.setString(1, searchName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(mapResultSetToEmployee(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean filterBySalary(double salary, String range) {
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
    public Optional<Employee> findById(int id) {
        try (PreparedStatement preparedStatement = BaseRepository.getConnection()
                .prepareStatement("SELECT * FROM employees WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToEmployee(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        try (PreparedStatement preparedStatement = BaseRepository.getConnection()
                .prepareStatement("INSERT INTO employees (name, dob, gender, salary, phone, department_id) VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, employee.getName());

            if (employee.getDob() != null) {
                preparedStatement.setDate(2, java.sql.Date.valueOf(employee.getDob()));
            } else {
                preparedStatement.setNull(2, java.sql.Types.DATE);
            }

            if (employee.getGender() != null) {
                preparedStatement.setString(3, employee.getGender().name());
            } else {
                preparedStatement.setNull(3, java.sql.Types.VARCHAR);
            }

            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getPhone());

            if (employee.getDepartmentId() != null) {
                preparedStatement.setInt(6, employee.getDepartmentId());
            } else {
                preparedStatement.setNull(6, java.sql.Types.INTEGER);
            }

            preparedStatement.executeUpdate();
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try (PreparedStatement preparedStatement = BaseRepository.getConnection()
                .prepareStatement("DELETE FROM employees WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm bổ trợ để tránh lặp code map dữ liệu
    private Employee mapResultSetToEmployee(ResultSet resultSet) throws SQLException {
        return Employee.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .dob(resultSet.getDate("dob") != null ? resultSet.getDate("dob").toLocalDate() : null)
                .gender(Gender.valueOf(resultSet.getString("gender").toUpperCase()))
                .salary(resultSet.getDouble("salary"))
                .phone(resultSet.getString("phone"))
                .departmentId(resultSet.getInt("department_id"))
                .build();
    }
}