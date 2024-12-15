package com.example.exercisejparelationi1.Service;

import com.example.exercisejparelationi1.ApiResponse.ApiException;
import com.example.exercisejparelationi1.DTO.AddressDTO;
import com.example.exercisejparelationi1.DTO.outDTO.StudentDTO;
import com.example.exercisejparelationi1.Model.Address;
import com.example.exercisejparelationi1.Model.Student;
import com.example.exercisejparelationi1.Model.Teacher;
import com.example.exercisejparelationi1.Repository.AddressRepository;
import com.example.exercisejparelationi1.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    public List<AddressDTO> getAll() {
        List<Address>addresses=addressRepository.findAll();

        List<AddressDTO> dtos=new ArrayList<>();
        for(Address a:addresses){
            AddressDTO addressDTO =new AddressDTO(a.getBuildingNumber(),a.getArea(),a.getStreet(),a.getTeacher().getId());
            dtos.add(addressDTO);
        }
        return  dtos;
    }
    public void add(AddressDTO addressDTO) {
        Teacher teacher=teacherRepository.findTeacherById(addressDTO.getTeacher_id());
        if(teacher==null){
            throw new ApiException("teacher not found");
        }

        Address address=new Address(null,addressDTO.getArea(),addressDTO.getStreet(),addressDTO.getBuildingNumber(),teacher);
        addressRepository.save(address);
    }



    public void update(AddressDTO addressDTO) {
        Address a = addressRepository.findAddressById(addressDTO.getTeacher_id());
        if (a == null) {
            throw new ApiException("address id not found");
        }
       a.setArea(addressDTO.getArea());
        a.setStreet(addressDTO.getStreet());
        a.setBuildingNumber(addressDTO.getBuildingNumber());
        addressRepository.save(a);
    }


}
