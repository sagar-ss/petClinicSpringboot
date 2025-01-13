package com.sagar.pet_clinic.dto;

import java.time.LocalDate;

public record PetRequestDto(
        String name,
        LocalDate birthDate,
        Integer owner_id
) {
}
