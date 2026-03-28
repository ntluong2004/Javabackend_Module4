package com.tlu.learning.ex_full.chapter2.chapter2_ex3;

import com.tlu.learning.ex_full.chapter2.chapter2_ex1.Gender;
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