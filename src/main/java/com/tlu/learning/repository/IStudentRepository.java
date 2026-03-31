package com.tlu.learning.repository;

import com.tlu.learning.model.Student;

import java.util.List;

public interface IStudentRepository {
    public List<Student> findAll();

    public Student findById(Integer id);

    public Student save(Student student);
}
