package com.tlu.learning.repository.impl;

import com.tlu.learning.model.Student;
import com.tlu.learning.repository.IStudentRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentRepository implements IStudentRepository {
    List<Student> students = new ArrayList<>();

    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();

        try (PreparedStatement preparedStatement = BaseRepository.getConnection()
                .prepareStatement("select id, name, score from student");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Student student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .score(resultSet.getDouble("score"))
                        .build();

                list.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public Student findById(Integer id) {

        try (PreparedStatement preparedStatement = BaseRepository.getConnection()
                .prepareStatement("select id, name, score from student where id = ?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .score(resultSet.getDouble("score"))
                        .build();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Student save(Student student) {
        try (PreparedStatement preparedStatement = BaseRepository.getConnection()
                .prepareStatement("insert into student (name, score) values (?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setDouble(2, student.getScore());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

}
