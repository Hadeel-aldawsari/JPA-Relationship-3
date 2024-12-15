package com.example.exercisejparelationi1.Repository;

import com.example.exercisejparelationi1.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findStudentById(Integer id);


}
