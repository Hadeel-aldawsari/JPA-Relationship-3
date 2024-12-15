package com.example.exercisejparelationi1.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(10) not null")
    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotNull(message = "age should not be empty")
    @Min(value = 5,message = "age should be at least 5")
    @Positive(message = "enter valued age")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @Column(columnDefinition = "varchar(40) not null")
    @NotEmpty(message = "major should not be empty")
    private String major;

    @ManyToMany
    @JsonIgnore
    private Set<Course> courses;
}
