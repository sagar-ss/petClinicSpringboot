package com.sagar.pet_clinic.dto;

public record OwnerUpdateResponseDto(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String telephone,
        String address,
        String city
) {
}
