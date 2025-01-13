package com.sagar.pet_clinic.controller;

import com.sagar.pet_clinic.dto.AppointmentRequestDto;
import com.sagar.pet_clinic.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequestDto requestDto){
        return appointmentService.createAppointment(requestDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable Integer id){
        return appointmentService.getAppointment(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@RequestBody AppointmentRequestDto requestDto, @PathVariable Integer id){
        return appointmentService.updateAppointment(requestDto, id);
    }
}
