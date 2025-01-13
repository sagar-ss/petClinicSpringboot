package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.AppointmentRequestDto;
import com.sagar.pet_clinic.model.Appointment;
import com.sagar.pet_clinic.model.Owner;
import com.sagar.pet_clinic.repository.AppointmentRepository;
import com.sagar.pet_clinic.repository.PetRepository;
import com.sagar.pet_clinic.repository.VetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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





    public ResponseEntity<?> createAppointment(AppointmentRequestDto requestDto) {
        try{
                validationService.validator(requestDto);
                var appointment = appointmentMapper.toAppointment(requestDto);
                Appointment savedAppointment = appointmentRepository.save(appointment);
                return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.toAppointmentResponseDto(savedAppointment));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getAppointment(Integer id) {
        try {
            var existingAppointment = appointmentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Appointment Not found"));
            return ResponseEntity.status(HttpStatus.OK).body(existingAppointment);
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    public ResponseEntity<?> getAllAppointments() {
        try {
            var listOfAppointments = appointmentRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(listOfAppointments);
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    public ResponseEntity<?> updateAppointment(AppointmentRequestDto requestDto, Integer id) {
        
        return null;
    }
}
