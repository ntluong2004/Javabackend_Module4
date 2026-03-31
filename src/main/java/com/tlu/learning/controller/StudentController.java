package com.tlu.learning.controller;

import com.tlu.learning.model.Student;
import com.tlu.learning.dto.ApiResponse;
import com.tlu.learning.exception.AppException;
import com.tlu.learning.exception.ErrorCode;
import com.tlu.learning.service.IStudentService;
import com.tlu.learning.service.impl.StudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
@FieldDefaults(level =AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/students")
public class StudentController {

//    //Có 3 cách để DI
//    // c1: tiêm đậu thông qua filed
//    @Autowired
//    private IStudentService studentServiceImpl;
//
    // c2: tiêm đậu thông qua constructor
    @Autowired // cách 2 này Autowired có cũng được không có cũng được
    IStudentService studentService;

    // c3: tiêm đậu thông qua setter
//    private IStudentService studentService;
//    @Autowired
//    public void setStudentService(IStudentService studentService) {
//        this.studentService = studentService;
//    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.findAll();
    }

    //     Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable Integer id) {

        Student student = studentService.findById(id);

        if (student != null) {
            return ResponseEntity.status(HttpStatus.OK).body(

                    ApiResponse.<Student>builder().
                            data(student).
                            build()
            );
        }
        throw new AppException(ErrorCode.STUDENT_NOTFOUND);
    }

    // Add new student
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student student) {

        studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Student>builder()
                        .data(student)
                        .build()
        );
    }
}
