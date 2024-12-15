package com.example.exercisejparelationi1.Service;

import com.example.exercisejparelationi1.ApiResponse.ApiException;
import com.example.exercisejparelationi1.DTO.AddressDTO;
import com.example.exercisejparelationi1.DTO.outDTO.CourseDTO;
import com.example.exercisejparelationi1.DTO.outDTO.StudentDTO;
import com.example.exercisejparelationi1.Model.Address;
import com.example.exercisejparelationi1.Model.Course;
import com.example.exercisejparelationi1.Model.Student;
import com.example.exercisejparelationi1.Model.Teacher;
import com.example.exercisejparelationi1.Repository.CourseRepository;
import com.example.exercisejparelationi1.Repository.StudentRepository;
import com.example.exercisejparelationi1.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;


    public List<CourseDTO> getAll(){
        List<Course>courses=courseRepository.findAll();

        List<CourseDTO> dtos=new ArrayList<>();
        for(Course c:courses){
            CourseDTO courseDTO =new CourseDTO(c.getName(),c.getTeacher());
            dtos.add(courseDTO);
        }
        return  dtos;
    }

    //add course and assign the teacher at the same time
        public void add(Integer teacherId, Course course){
        Teacher t=teacherRepository.findTeacherById(teacherId);if(t==null)throw new ApiException("there is no teacher with this id");

        course.setTeacher(t);
        courseRepository.save(course);
    }

    public void update(Integer courseId,Course course){
        Course c=courseRepository.findCourseById(courseId);
        if(c==null)throw new ApiException("there is no course with this id");

        c.setName(course.getName());
        courseRepository.save(c);
    }

    public void delete(Integer courseId){
        Course c=courseRepository.findCourseById(courseId);
        if(c==null)throw new ApiException("there course with this id ");
        courseRepository.delete(c);
    }

    public String getTeacherName(Integer courseId){
        Course c=courseRepository.findCourseById(courseId);
        if(c==null)throw new ApiException("there course with this id ");

        Teacher teacher=c.getTeacher();
        return teacher.getName();
    }



    public List<StudentDTO> getStudentByCourse(Integer course_id){
        Course course=courseRepository.findCourseById(course_id);
        if(course==null) throw new ApiException("course not found");

       List<Student> students=studentRepository.findAll();
       List<StudentDTO> dtos=new ArrayList<>();

       for(Student s:students){
           for(Course c:s.getCourses()){
               if(c.getId()==course_id){
                   StudentDTO studentDTO=new StudentDTO(s.getId(),s.getName(),s.getAge(),s.getMajor());
                   dtos.add(studentDTO);
               }
           }
       }
return dtos;

    }





}
