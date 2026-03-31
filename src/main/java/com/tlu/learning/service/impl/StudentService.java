package com.tlu.learning.service.impl;

import com.tlu.learning.repository.IStudentRepository;
import com.tlu.learning.repository.impl.StudentRepository;
import com.tlu.learning.model.Student;
import com.tlu.learning.service.IStudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentService implements IStudentService {

    IStudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
