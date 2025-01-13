package com.sagar.pet_clinic.repository;

import com.sagar.pet_clinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Integer> {
}
