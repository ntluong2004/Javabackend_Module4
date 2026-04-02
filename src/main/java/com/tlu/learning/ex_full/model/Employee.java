package com.tlu.learning.ex_full.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonFormat(pattern = "yyyy-MM-dd")
public class Employee {
    Integer id;
    String name;
    LocalDate dob;
    Gender gender;
    double salary;
    String phone;
    Integer departmentId;
}