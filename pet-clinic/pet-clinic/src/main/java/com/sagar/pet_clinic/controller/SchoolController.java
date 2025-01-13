package com.sagar.pet_clinic.controller;

import com.sagar.pet_clinic.model.School;
import com.sagar.pet_clinic.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("api/school")
public class SchoolController {

    private SchoolRepository schoolRepo;

    @PostMapping
    public School create(@RequestBody School school){
        return schoolRepo.save(school);
    }

    @GetMapping("/{school_id}")
    public School get(@PathVariable Integer school_id){
        Optional<School> school = schoolRepo.findById(school_id);
        return school.get();
    }



}
