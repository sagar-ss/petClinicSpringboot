package com.sagar.pet_clinic.controller;

import com.sagar.pet_clinic.dto.AppointmentRequestDto;
import com.sagar.pet_clinic.dto.AppointmentResponseDto;
import com.sagar.pet_clinic.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequestDto requestDto){
        AppointmentResponseDto appointment = appointmentService.createAppointment(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(appointment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable Integer id){
        AppointmentResponseDto appointment = appointmentService.getAppointment(id);
        return ResponseEntity.status(HttpStatus.OK).body(appointment);
    }

    @GetMapping
    public ResponseEntity<?> getAllAppointments(){
        List<AppointmentResponseDto> allAppointments = appointmentService.getAllAppointments();
        return ResponseEntity.status(HttpStatus.OK).body(allAppointments);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@RequestBody AppointmentRequestDto requestDto, @PathVariable Integer id){
        return appointmentService.updateAppointment(requestDto, id);
    }
}
