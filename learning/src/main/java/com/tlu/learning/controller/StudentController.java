package com.tlu.learning.controller;

import com.tlu.learning.Student;
import com.tlu.learning.dto.ApiResponse;
import com.tlu.learning.exception.AppException;
import com.tlu.learning.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students =
            new ArrayList<>(
                    Arrays.asList(
                            new Student(1, "Lương", 2.0),
                            new Student(2, "Luân", 2.5),
                            new Student(3, "Thiên", 2.3)
                    ));

    // Get list API (search)
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Integer id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        ApiResponse.<Student>builder()
                                .data(student)
                                .build()
                );
            }
        }
//        return ResponseEntity.status(ErrorCode.STUDENT_NOTFOUND.getStatus()).body(
//                ApiResponse.<Student>builder()
//                        .code(ErrorCode.STUDENT_NOTFOUND.getCode())
//                        .message(ErrorCode.STUDENT_NOTFOUND.getMessage())
//                        .build()
//        );
        throw new AppException(ErrorCode.STUDENT_NOTFOUND);
    }

    // Add new student
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody Student student) {
        student.setId((int) (Math.random() * 10000));
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Student>builder()
                        .data(student)
                        .build()
        );
    }

}
