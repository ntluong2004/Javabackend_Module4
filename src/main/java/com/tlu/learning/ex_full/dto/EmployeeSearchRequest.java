package com.tlu.learning.ex_full.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tlu.learning.ex_full.model.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonFormat(pattern = "yyyy-MM-dd")
public class EmployeeSearchRequest {
    int id;
    String name;
    LocalDate dob;
    Gender gender;
    double salary;
    String phone;
    int departmentId;
}