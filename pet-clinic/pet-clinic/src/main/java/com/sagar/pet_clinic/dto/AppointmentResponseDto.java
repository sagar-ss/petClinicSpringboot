package com.sagar.pet_clinic.dto;

import com.sagar.pet_clinic.model.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentResponseDto(
        String description,
        LocalDateTime appointmentDateAndTime,
        AppointmentStatus appointmentStatus
) {
}
