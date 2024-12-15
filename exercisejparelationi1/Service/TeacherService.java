package com.example.exercisejparelationi1.Service;


import com.example.exercisejparelationi1.ApiResponse.ApiException;
import com.example.exercisejparelationi1.DTO.AddressDTO;
import com.example.exercisejparelationi1.DTO.outDTO.TeacherDTO;
import com.example.exercisejparelationi1.Model.Address;
import com.example.exercisejparelationi1.Model.Teacher;
import com.example.exercisejparelationi1.Repository.AddressRepository;
import com.example.exercisejparelationi1.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    public List<TeacherDTO> getAll() {
        List<Teacher>teachers=teacherRepository.findAll();

        List<TeacherDTO> dtos=new ArrayList<>();
        for(Teacher t:teachers){
            TeacherDTO teacherDTO =new TeacherDTO(t.getName(),t.getAge(),t.getEmail(),t.getSalary());
            dtos.add(teacherDTO);
        }
        return  dtos;
    }
    public void add(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void update(Integer id,Teacher teacher) {
        Teacher t = teacherRepository.findTeacherById(id);
        if (t == null) {
            throw new ApiException("teacher id not found");
        }
        t.setName(teacher.getName());
        t.setAge(teacher.getAge());
        t.setEmail(teacher.getEmail());
        t.setSalary(teacher.getSalary());
        teacherRepository.save(t);
    }

    public void delete(Integer id) {
        Teacher t = teacherRepository.findTeacherById(id);
        if (t == null) {
            throw new ApiException("teacher id not found");
        }
        Address a=addressRepository.findAddressById(id);
        addressRepository.delete(a);
        teacherRepository.delete(t);
    }


    public TeacherDTO getTeacherDetails(Integer id){
        Teacher t = teacherRepository.findTeacherById(id);
        if (t == null) {
            throw new ApiException("teacher id not found");
        }

        return new TeacherDTO(t.getName(),t.getAge(),t.getEmail(),t.getSalary());

    }
}
