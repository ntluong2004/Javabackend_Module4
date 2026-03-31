package com.tlu.learning.ex_full.chapter1.chapter1_ex4;

import com.tlu.learning.ex_full.chapter1.chapter1_ex4.Employee;
import com.tlu.learning.ex_full.chapter1.chapter1_ex4.Gender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController("employeeEx4Controller")
@RequestMapping("/ex4/employees") // Tiền tố chung cho tất cả API trong class này
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        // Khởi tạo dữ liệu mẫu
        employees.add(new Employee("Luong", "2000-01-01", Gender.MALE, 1500, "0912345678"));
        employees.add(new Employee("Hoa", "1998-05-15", Gender.FEMALE, 2000, "0988888888"));
    }

    // 1. GET ALL
    @GetMapping
    public List<Employee> getAll() {
        return employees;
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy nhân viên ID: " + id));
    }

    // 3. POST (CREATE)
    @PostMapping
    public Employee create(@RequestBody Employee newEmployee) {
        newEmployee.setId(UUID.randomUUID().toString()); // Gán ID mới
        employees.add(newEmployee);
        return newEmployee;
    }

    // 4. PUT (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody Employee updatedInfo) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                e.setName(updatedInfo.getName());
                e.setDob(updatedInfo.getDob());
                e.setGender(updatedInfo.getGender());
                e.setSalary(updatedInfo.getSalary());
                e.setPhone(updatedInfo.getPhone());
                return ResponseEntity.ok(e);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không thể cập nhật, ID không tồn tại.");
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        boolean removed = employees.removeIf(e -> e.getId().equals(id));
        if (removed) {
            return ResponseEntity.ok("Xóa thành công nhân viên ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy để xóa.");
    }
}