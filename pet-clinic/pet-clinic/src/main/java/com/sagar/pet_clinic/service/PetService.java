package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.PetRequestDto;
import com.sagar.pet_clinic.model.Owner;
import com.sagar.pet_clinic.repository.OwnerRepository;
import com.sagar.pet_clinic.repository.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetService(OwnerRepository ownerRepository, PetRepository petRepository, PetMapper petMapper) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    // CREATE PET
    public ResponseEntity<?> createPet(PetRequestDto requestDto) {
        try {

            Owner existingOwner = ownerRepository.findById(requestDto.owner_id())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid owner_id"));

            var pet = petMapper.toPet(requestDto);
            existingOwner.getPets().add(pet);
            var savedPet = petRepository.save(pet);
            return ResponseEntity.status(HttpStatus.CREATED).body(petMapper.toPetResponseDto(savedPet));
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // UPDATE PET
    public ResponseEntity<?> updatePet(PetRequestDto requestDto, Integer id) {
        try {

            var existingPet = petRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid pet_id"));

            if(requestDto.name()!=null){
                existingPet.setName(requestDto.name());
            }
            if(requestDto.birthDate()!=null){
                existingPet.setBirthDate(requestDto.birthDate());
            }
            var savedPet = petRepository.save(existingPet);
            return ResponseEntity.status(HttpStatus.CREATED).body(petMapper.toPetResponseDto(savedPet));
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    // DELETE PET
    public ResponseEntity<?> deletePet(Integer id) {
        try {
            var existingPet = petRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Pet not found"));

            existingPet.getOwner().getPets().remove(existingPet);
            petRepository.delete(existingPet);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET PET
    public ResponseEntity<?> getPet(Integer id){
        try {
            var existingPet = petRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Pet not found"));

            return ResponseEntity.status(HttpStatus.OK).body(existingPet);
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    public ResponseEntity<?> findAllPetsOfOwner(Integer ownerId) {
        try {

            Owner existingOwner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid owner_id"));

            return ResponseEntity.status(HttpStatus.OK).body(existingOwner.getPets());
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
