package com.sagar.pet_clinic.controller;

import com.sagar.pet_clinic.dto.VetRequestDto;
import com.sagar.pet_clinic.dto.VetResponseDto;
import com.sagar.pet_clinic.model.Appointment;
import com.sagar.pet_clinic.service.VetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vets")
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @PostMapping
    public ResponseEntity<?> createVet(@Valid @RequestBody VetRequestDto requestDto){
        VetResponseDto vet = vetService.createVet(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVet(@RequestBody VetRequestDto requestDto, @PathVariable Integer id){
        VetResponseDto vetResponseDto = vetService.updateVet(requestDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(vetResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVet(@PathVariable Integer id){
        VetResponseDto vet = vetService.getVet(id);
        return ResponseEntity.status(HttpStatus.OK).body(vet);
    }

    @GetMapping
    public ResponseEntity<?> getAllVets(){
        List<VetResponseDto> allVets = vetService.getAllVets();
        return ResponseEntity.status(HttpStatus.OK).body(allVets);

    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<?> getAllAppointmentOfVet(@PathVariable Integer id){
        List<Appointment> allAppointmentOfVet = vetService.getAllAppointmentOfVet(id);
        return ResponseEntity.status(HttpStatus.OK).body(allAppointmentOfVet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVet(@PathVariable Integer id){
        vetService.deleteVet(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
