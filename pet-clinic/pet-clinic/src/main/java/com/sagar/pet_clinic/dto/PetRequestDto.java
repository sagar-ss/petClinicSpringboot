package com.sagar.pet_clinic.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record PetRequestDto(
        @NotEmpty(message = "name must not be empty")
        String name,
        @NotEmpty(message = "birthDate must not be empty")
        LocalDate birthDate,
        @NotEmpty(message = "owner_id must not be empty")
        Integer owner_id
) {
}
