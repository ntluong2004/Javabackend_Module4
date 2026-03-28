package com.tlu.learning.ex_full;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class Employee {
    String id;
    String name;
    LocalDate dob;
    Gender gender;
    Double salary;
    String phone;
}


