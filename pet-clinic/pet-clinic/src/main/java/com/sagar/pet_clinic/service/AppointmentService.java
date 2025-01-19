package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.AppointmentRequestDto;
import com.sagar.pet_clinic.dto.AppointmentResponseDto;
import com.sagar.pet_clinic.model.Appointment;
import com.sagar.pet_clinic.repository.AppointmentRepository;
import com.sagar.pet_clinic.repository.PetRepository;
import com.sagar.pet_clinic.repository.VetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final ValidationService validationService;
    private final PetRepository petRepository;
    private final VetRepository vetRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, ValidationService validationService, PetRepository petRepository, VetRepository vetRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.validationService = validationService;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
    }

    public AppointmentResponseDto createAppointment(AppointmentRequestDto requestDto) {
                validationService.validator(requestDto);
                var appointment = appointmentMapper.toAppointment(requestDto);
                Appointment savedAppointment = appointmentRepository.save(appointment);
                return appointmentMapper.toAppointmentResponseDto(savedAppointment);
    }

    public AppointmentResponseDto getAppointment(Integer id) {
            var existingAppointment = appointmentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Appointment Not found"));
            return appointmentMapper.toAppointmentResponseDto(existingAppointment);
    }


    public List<AppointmentResponseDto> getAllAppointments() {
            var listOfAppointments = appointmentRepository.findAll();
            return listOfAppointments.stream().map(appointmentMapper :: toAppointmentResponseDto).collect(Collectors.toList());
    }


    public ResponseEntity<?> updateAppointment(AppointmentRequestDto requestDto, Integer id) {
        
        return null;
    }
}
