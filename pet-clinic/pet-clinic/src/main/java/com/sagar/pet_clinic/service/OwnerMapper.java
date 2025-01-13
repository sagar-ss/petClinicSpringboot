package com.sagar.pet_clinic.service;


import com.sagar.pet_clinic.dto.OwnerRequestDto;
import com.sagar.pet_clinic.dto.OwnerResponseDto;
import com.sagar.pet_clinic.model.Owner;
import org.springframework.stereotype.Service;

@Service
public class OwnerMapper {

    public OwnerResponseDto toOwnerResponseDto(Owner savedOwner) {
        OwnerResponseDto dto = new OwnerResponseDto(savedOwner.getId());
        return dto;
    }

    public Owner toOwner(OwnerRequestDto dto){
        Owner owner = new Owner();
        owner.setFirstName(dto.firstName());
        owner.setLastName(dto.lastName());
        owner.setEmail(dto.email());
        owner.setTelephone(dto.telephone());
        return owner;
    }

}
