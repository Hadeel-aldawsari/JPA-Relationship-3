package com.example.exercisejparelationi1.Controller;


import com.example.exercisejparelationi1.ApiResponse.ApiResponse;
import com.example.exercisejparelationi1.DTO.AddressDTO;
import com.example.exercisejparelationi1.DTO.outDTO.StudentDTO;
import com.example.exercisejparelationi1.Model.Course;
import com.example.exercisejparelationi1.Model.Student;
import com.example.exercisejparelationi1.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(studentService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Student student){
        studentService.add(student);
        return ResponseEntity.status(200).body(new ApiResponse("student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Student student){
        studentService.update(id,student);
        return ResponseEntity.status(200).body(new ApiResponse("student updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        studentService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("student deleted successfully"));
    }


    @PutMapping("/assign-course-to-student/{course_id}/{student_id}")
    public ResponseEntity AssignCourseToStudent(@PathVariable Integer course_id,@PathVariable Integer student_id){
        studentService.AssignCourseToStudent(course_id,student_id);
        return ResponseEntity.status(200).body(new ApiResponse("assign done"));
    }


    @PutMapping("/change-major/{student_id}/{major}")
    public ResponseEntity changeMajor(@PathVariable Integer student_id,@PathVariable String major){
        studentService.changeMajor(student_id,major);
      return ResponseEntity.status(200).body(new ApiResponse("student major changed successfully"));
    }



}
