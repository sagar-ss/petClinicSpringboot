package com.sagar.pet_clinic.controller;

import com.sagar.pet_clinic.dto.PetRequestDto;
import com.sagar.pet_clinic.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets/")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<?> createPet(@RequestBody PetRequestDto requestDto ){
        return petService.createPet(requestDto);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@RequestBody PetRequestDto requestDto, @PathVariable Integer id){
        return petService.updatePet(requestDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Integer id){
        return petService.deletePet(id);
    }

    @GetMapping("/owner/{owner_id}")
    public ResponseEntity<?> findAllPetsOfOwner(@PathVariable Integer owner_id){
        return petService.findAllPetsOfOwner(owner_id);
    }
}
