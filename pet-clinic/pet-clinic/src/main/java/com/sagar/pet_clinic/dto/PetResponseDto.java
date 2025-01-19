package com.sagar.pet_clinic.dto;

import java.time.LocalDate;

public record PetResponseDto(
        Integer id,
        String name,
        LocalDate dateOfBirth
) {
}
