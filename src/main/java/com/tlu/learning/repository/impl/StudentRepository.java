package com.tlu.learning.repository.impl;

import com.tlu.learning.model.Student;
import com.tlu.learning.repository.IStudentRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentRepository implements IStudentRepository {
    List<Student> students =
            new ArrayList<>(
                    Arrays.asList(
                            new Student(1, "Lương", 2.0),
                            new Student(2, "Luân", 2.5),
                            new Student(3, "Thiên", 2.3)
                    ));

    public List<Student> findAll() {
        return students;
    }

    public Student findById(Integer id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public Student save(Student student) {
        students.add(student);

        return student;
    }

}
