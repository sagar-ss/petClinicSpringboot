package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.AppointmentRequestDto;
import com.sagar.pet_clinic.repository.PetRepository;
import com.sagar.pet_clinic.repository.VetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private final PetRepository petRepository;
    private final VetRepository vetRepository;
    private final AppointmentMapper appointmentMapper;

    public ValidationService(PetRepository petRepository, VetRepository vetRepository, AppointmentMapper appointmentMapper) {
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.appointmentMapper = appointmentMapper;
    }


    public  void validator(AppointmentRequestDto requestDto){

            var existingVet = vetRepository.findById(requestDto.vet_id())
                    .orElseThrow(() -> new IllegalArgumentException("Vet not found"));
            var existingPet = petRepository.findById(requestDto.pet_id())
                    .orElseThrow(() -> new IllegalArgumentException("Pet not found"));

            var appointment = appointmentMapper.toAppointment(requestDto);
            existingPet.getAppointments().add(appointment);
            existingVet.getAppointments().add(appointment);

    }



}
