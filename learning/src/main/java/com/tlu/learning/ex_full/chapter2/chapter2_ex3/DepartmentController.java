package com.tlu.learning.ex_full.chapter2.chapter2_ex3;

import com.tlu.learning.ex_full.chapter2.chapter2_ex2.ApiResponse;
import com.tlu.learning.ex_full.chapter2.chapter2_ex2.AppException;
import com.tlu.learning.ex_full.chapter2.chapter2_ex2.ErrorCode;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController("deptControllerEx3")
@RequestMapping("/chapter2/ex3/departments")
public class DepartmentController {
    private final List<Department> departments = new ArrayList<>();

    public DepartmentController() {
        departments.add(new Department("DEPT_IT", "Information Technology"));
        departments.add(new Department("DEPT_HR", "Human Resources"));
    }

    @GetMapping
    public ApiResponse<List<Department>> getAll() {
        return ApiResponse.<List<Department>>builder().result(departments).build();
    }

    @PostMapping
    public ApiResponse<Department> create(@RequestBody Department request) {
        request.setId("DEPT_" + UUID.randomUUID().toString().substring(0, 5).toUpperCase());
        departments.add(request);
        return ApiResponse.<Department>builder().result(request).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        boolean removed = departments.removeIf(d -> d.getId().equals(id));
        if (!removed) throw new AppException(ErrorCode.USER_NOT_FOUND); // Reuse or add DEPT_NOT_FOUND to ErrorCode
        return ApiResponse.<String>builder().message("Department deleted").build();
    }
}