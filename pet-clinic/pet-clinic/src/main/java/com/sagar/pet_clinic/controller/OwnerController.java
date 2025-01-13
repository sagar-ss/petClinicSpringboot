package com.sagar.pet_clinic.controller;

import com.sagar.pet_clinic.dto.OwnerRequestDto;
import com.sagar.pet_clinic.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<?> createOwner(@RequestBody OwnerRequestDto requestDto){
        return ownerService.createOwner(requestDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOwner(@RequestBody OwnerRequestDto requestDto, @PathVariable Integer id){
        return ownerService.updateOwner(requestDto, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOwner(@PathVariable Integer id){
        return ownerService.getOwner(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllOwner(){
        return ownerService.getAllOwners();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Integer id){
        return ownerService.deleteOwner(id);
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<?> getAllPets(@PathVariable Integer id){
        return ownerService.findAllPets(id);
    }

}
