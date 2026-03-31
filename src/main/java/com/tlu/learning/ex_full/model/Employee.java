package com.tlu.learning.ex_full.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    String id;
    String name;
    String dob;
    Gender gender;
    double salary;
    String phone;
    String departmentId;
}