package com.sagar.pet_clinic.repository;

import com.sagar.pet_clinic.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet,Integer> {
}
