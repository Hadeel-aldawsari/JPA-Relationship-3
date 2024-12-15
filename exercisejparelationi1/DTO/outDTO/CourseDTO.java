package com.example.exercisejparelationi1.DTO.outDTO;

import com.example.exercisejparelationi1.Model.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseDTO {
    private String name;
    private Teacher teacher;

}
