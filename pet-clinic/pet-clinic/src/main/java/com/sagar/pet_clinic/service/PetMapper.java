package com.sagar.pet_clinic.service;


import com.sagar.pet_clinic.dto.PetRequestDto;
import com.sagar.pet_clinic.dto.PetResponseDto;
import com.sagar.pet_clinic.model.Owner;
import com.sagar.pet_clinic.model.Pet;
import org.springframework.stereotype.Service;

@Service
public class PetMapper {

    public PetResponseDto toPetResponseDto(Pet pet) {
        PetResponseDto dto = new PetResponseDto(pet.getId(),pet.getName(),pet.getBirthDate() );
        return dto;
    }

    public Pet toPet(PetRequestDto dto){
        Pet pet = new Pet();
        pet.setName(dto.name());
        pet.setBirthDate(dto.birthDate());
        Owner owner = new Owner();
        owner.setId(dto.owner_id());
        pet.setOwner(owner);
        return pet;
    }

}
