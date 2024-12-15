package com.example.exercisejparelationi1.DTO.outDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherDTO {

    private String name;

    private Integer age;

    private String email;

    private Double salary;
}
