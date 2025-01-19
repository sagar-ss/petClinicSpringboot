package com.sagar.pet_clinic.dto;

import jakarta.validation.constraints.NotEmpty;

public record VetRequestDto(
        @NotEmpty(message = "name must not be empty")
        String name,
        @NotEmpty(message = "specialization must not be empty")
        String specialization,
        @NotEmpty(message = "address must not be empty")
        String address
) {
}
