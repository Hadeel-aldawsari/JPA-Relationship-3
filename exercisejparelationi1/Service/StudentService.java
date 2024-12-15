package com.example.exercisejparelationi1.Service;

import com.example.exercisejparelationi1.ApiResponse.ApiException;
import com.example.exercisejparelationi1.DTO.outDTO.StudentDTO;
import com.example.exercisejparelationi1.Model.Course;
import com.example.exercisejparelationi1.Model.Student;
import com.example.exercisejparelationi1.Repository.CourseRepository;
import com.example.exercisejparelationi1.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<StudentDTO> getAll(){
        List<Student>students=studentRepository.findAll();

        List<StudentDTO> dtos=new ArrayList<>();
        for(Student s:students){
            StudentDTO studentDTO =new StudentDTO(s.getId(),s.getName(),s.getAge(),s.getMajor());
            dtos.add(studentDTO);
        }
        return  dtos;
    }


    //add
    public void add(Student student){
        studentRepository.save(student);
    }

    public void update(Integer id,Student student) {
        Student student1 = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("student not found");
        }
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setMajor(student.getMajor());
        studentRepository.save(student);
    }

    public void delete(Integer id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("student not found");
        }
        studentRepository.delete(student);
    }

    public void AssignCourseToStudent(Integer course_id,Integer student_id){
        Student s=studentRepository.findStudentById(student_id);
        Course c=courseRepository.findCourseById(course_id);
        //check if null

        if(s ==null || c==null){
            throw new ApiException("can't Assign");
        }

        c.getStudents().add(s);
        s.getCourses().add(c);

        courseRepository.save(c);
        studentRepository.save(s);
    }



    public void changeMajor(Integer student_id, String major){
        Student s=studentRepository.findStudentById(student_id);
        if(s==null){
            throw new ApiException("student not found");
        }
        s.setMajor(major);
        s.setCourses(null);
        studentRepository.save(s);

    }

}
