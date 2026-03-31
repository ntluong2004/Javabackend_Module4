package com.tlu.learning.service;

import com.tlu.learning.model.Student;

import java.util.List;

public interface IStudentService {

    List<Student> findAll();

    Student findById(Integer id);

    Student save(Student student);

}
