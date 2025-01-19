package com.sagar.pet_clinic.controller;

import com.sagar.pet_clinic.dto.OwnerRequestDto;
import com.sagar.pet_clinic.dto.OwnerResponseDto;
import com.sagar.pet_clinic.dto.OwnerUpdateRequestDto;
import com.sagar.pet_clinic.dto.OwnerUpdateResponseDto;
import com.sagar.pet_clinic.model.Owner;
import com.sagar.pet_clinic.model.Pet;
import com.sagar.pet_clinic.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<?> createOwner(@Valid @RequestBody OwnerRequestDto requestDto){
        OwnerResponseDto owner = ownerService.createOwner(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(owner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOwner(@RequestBody OwnerUpdateRequestDto requestDto, @PathVariable Integer id){
        OwnerUpdateResponseDto ownerUpdateResponseDto = ownerService.updateOwner(requestDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(ownerUpdateResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOwner(@PathVariable Integer id){
        OwnerResponseDto owner = ownerService.getOwner(id);
        return ResponseEntity.status(HttpStatus.OK).body(owner);
    }

    @GetMapping
    public ResponseEntity<?> getAllOwner(){
        List<OwnerResponseDto> allOwners = ownerService.getAllOwners();
        return ResponseEntity.status(HttpStatus.OK).body(allOwners);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Integer id){
        ownerService.deleteOwner(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<?> getAllPets(@PathVariable Integer id){
        List<Pet> allPets = ownerService.findAllPets(id);
        return ResponseEntity.status(HttpStatus.OK).body(allPets);
    }

}
