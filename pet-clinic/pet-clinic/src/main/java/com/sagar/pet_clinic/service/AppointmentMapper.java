package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.AppointmentRequestDto;
import com.sagar.pet_clinic.dto.AppointmentResponseDto;
import com.sagar.pet_clinic.model.Appointment;
import com.sagar.pet_clinic.model.Pet;
import com.sagar.pet_clinic.model.Vet;
import org.springframework.stereotype.Service;

@Service
public class AppointmentMapper {

    public Appointment toAppointment(AppointmentRequestDto requestDto){

        Appointment appointment = new Appointment();
        appointment.setAppointmentDateAndTime(requestDto.appointmentDateAndTime());
        appointment.setDescription(requestDto.description());
        Vet vet = new Vet();
        vet.setId(requestDto.vet_id());
        appointment.setVet(vet);
        Pet pet = new Pet();
        pet.setId(requestDto.pet_id());
        appointment.setPet(pet);
        return appointment;
    }

    public AppointmentResponseDto toAppointmentResponseDto(Appointment appointment){
        AppointmentResponseDto dto = new AppointmentResponseDto(appointment.getDescription(), appointment.getAppointmentDateAndTime(), appointment.getStatus());
        return dto;
    }
}
