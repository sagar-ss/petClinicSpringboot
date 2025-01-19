package com.sagar.pet_clinic.controller;

import com.sagar.pet_clinic.dto.PetRequestDto;
import com.sagar.pet_clinic.dto.PetResponseDto;
import com.sagar.pet_clinic.model.Pet;
import com.sagar.pet_clinic.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets/")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<?> createPet(@Valid @RequestBody PetRequestDto requestDto ){
        PetResponseDto pet = petService.createPet(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@RequestBody PetRequestDto requestDto, @PathVariable Integer id){
        PetResponseDto petResponseDto = petService.updatePet(requestDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(petResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPet(@PathVariable Integer id){
        PetResponseDto pet = petService.getPet(id);
        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Integer id){
        petService.deletePet(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/owner/{owner_id}")
    public ResponseEntity<?> findAllPetsOfOwner(@PathVariable Integer owner_id){
        List<Pet> allPetsOfOwner = petService.findAllPetsOfOwner(owner_id);
        return ResponseEntity.status(HttpStatus.OK).body(allPetsOfOwner);


    }
}
