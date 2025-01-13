package com.sagar.pet_clinic.dto;

import java.time.LocalDateTime;

public record AppointmentRequestDto(
        Integer vet_id,
        Integer pet_id,
        LocalDateTime appointmentDateAndTime,
        String description

) {
}
