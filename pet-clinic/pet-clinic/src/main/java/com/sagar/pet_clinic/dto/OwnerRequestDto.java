package com.sagar.pet_clinic.dto;

public record OwnerRequestDto(
        String firstName,
        String lastName,
        String email,
        String telephone
) {
}
