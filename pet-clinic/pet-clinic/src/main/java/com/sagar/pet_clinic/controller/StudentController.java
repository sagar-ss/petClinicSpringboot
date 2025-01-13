package com.sagar.pet_clinic.controller;

import com.sagar.pet_clinic.model.Student;
import com.sagar.pet_clinic.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentRepository studentRepo;

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepo.save(student);
    }
}
