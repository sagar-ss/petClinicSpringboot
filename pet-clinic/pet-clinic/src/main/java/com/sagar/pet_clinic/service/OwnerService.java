package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.OwnerRequestDto;
import com.sagar.pet_clinic.model.Owner;
import com.sagar.pet_clinic.repository.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OwnerService {

    private final PetService petService;
    private final AppointmentService appointmentService;
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public OwnerService(PetService petService, AppointmentService appointmentService, OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.petService = petService;
        this.appointmentService = appointmentService;
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    // CREATE OWNER
    public ResponseEntity<?> createOwner(OwnerRequestDto requestDto) {
        try {
            var owner = ownerMapper.toOwner(requestDto);
            var savedOwner = ownerRepository.save(owner);
            return ResponseEntity.status(HttpStatus.CREATED).body(ownerMapper.toOwnerResponseDto(savedOwner));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    // UPDATE OWNER
    public ResponseEntity<?> updateOwner(OwnerRequestDto requestDto, Integer id) {
        try {
            // Find the existing owner by ID
            Owner existingOwner = ownerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid owner_id"));
            if (requestDto.firstName() != null) {
                existingOwner.setFirstName(requestDto.firstName());
            }
            if (requestDto.lastName() != null) {
                existingOwner.setLastName(requestDto.lastName());
            }
            if (requestDto.email() != null) {
                existingOwner.setEmail(requestDto.email());
            }
            if (requestDto.telephone() != null) {
                existingOwner.setTelephone(requestDto.telephone());
            }

            Owner updatedOwner = ownerRepository.save(existingOwner);

            return ResponseEntity.status(HttpStatus.OK).body(ownerMapper.toOwnerResponseDto(updatedOwner));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    // GET OWNER
    public ResponseEntity<?> getOwner(Integer id){
        try {
            Owner existingOwner = ownerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid owner_id"));
            return ResponseEntity.status(HttpStatus.OK).body(existingOwner);
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET ALL OWNERS

    public ResponseEntity<?> getAllOwners(){
        try {
            List<Owner> existingOwner = ownerRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(existingOwner);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // DELETE OWNER
    public ResponseEntity<?> deleteOwner(Integer id){
        try {
            Owner existingOwner = ownerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid owner_id"));
            ownerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // FIND ALL PETS OF A OWNER
    public ResponseEntity<?> findAllPets(Integer id){
        try {
            Owner existingOwner = ownerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid owner_id"));
            return ResponseEntity.status(HttpStatus.OK).body(existingOwner.getPets());
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
