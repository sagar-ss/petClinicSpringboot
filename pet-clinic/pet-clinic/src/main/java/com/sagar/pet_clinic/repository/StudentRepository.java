package com.sagar.pet_clinic.repository;

import com.sagar.pet_clinic.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
