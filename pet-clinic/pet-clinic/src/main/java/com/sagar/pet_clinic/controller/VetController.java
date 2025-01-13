package com.sagar.pet_clinic.controller;

import com.sagar.pet_clinic.dto.VetRequestDto;
import com.sagar.pet_clinic.service.VetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vets")
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @PostMapping
    public ResponseEntity<?> createVet(@RequestBody VetRequestDto requestDto){
        return vetService.createVet(requestDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVet(@RequestBody VetRequestDto requestDto, @PathVariable Integer id){
        return vetService.updateVet(requestDto, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVet(@PathVariable Integer id){
        return vetService.getVet(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllVets(){
        return vetService.getAllVets();
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<?> getAllAppointmentOfVet(@PathVariable Integer id){
        return vetService.getAllAppointmentOfVet(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVet(@PathVariable Integer id){
        return vetService.deleteVet(id);
    }



}
