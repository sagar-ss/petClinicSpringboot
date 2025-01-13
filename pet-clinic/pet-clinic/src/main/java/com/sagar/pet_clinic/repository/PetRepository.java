package com.sagar.pet_clinic.repository;

import com.sagar.pet_clinic.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet,Integer> {
}
