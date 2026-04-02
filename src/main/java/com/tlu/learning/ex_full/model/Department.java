package com.tlu.learning.ex_full.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)

public class Department {
    Integer id;
    String name;
}
