package com.sagar.pet_clinic.dto;

public record VetRequestDto(
        String name,
        String specialization,
        String address
) {
}
