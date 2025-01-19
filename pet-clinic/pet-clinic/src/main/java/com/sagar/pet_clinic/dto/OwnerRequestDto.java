package com.sagar.pet_clinic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record OwnerRequestDto(
       @NotEmpty(message =  "firstName must not be empty ")
        String firstName,
        @NotEmpty(message = "lastName must not be empty")
        String lastName,
        @NotEmpty(message = "email must not be empty")
        @Email(message = "email must be valid email address")
        String email,
        @NotEmpty(message = "telephone must not be empty")
        @Pattern(regexp = "\\d{10}", message = "Telephone must be a valid 10-digit number")
        String telephone
) {
}
