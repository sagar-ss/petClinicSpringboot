package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.OwnerRequestDto;
import com.sagar.pet_clinic.dto.OwnerResponseDto;
import com.sagar.pet_clinic.dto.VetRequestDto;
import com.sagar.pet_clinic.dto.VetResponseDto;
import com.sagar.pet_clinic.model.Owner;
import com.sagar.pet_clinic.model.Vet;
import org.springframework.stereotype.Service;

@Service
public class VetMapper {

    public VetResponseDto toVetResponseDto(Vet savedVet) {
        VetResponseDto dto = new VetResponseDto(savedVet.getId());
        return dto;
    }

    public Vet toVet(VetRequestDto dto){
        var vet = new Vet();
        vet.setName(dto.name());
        vet.setSpecialization(dto.specialization());
        vet.setAddress(dto.address());
        return vet;
    }
}
