package com.sagar.pet_clinic.dto;

public record OwnerUpdateRequestDto(
        String firstName,
        String lastName,
        String email,
        String telephone,
        String address,
        String city
) {
}
