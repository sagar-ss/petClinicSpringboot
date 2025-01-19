package com.sagar.pet_clinic.dto;

public record VetResponseDto(
        Integer id,
        String name,
        String specialization,
        String address
) {
}
