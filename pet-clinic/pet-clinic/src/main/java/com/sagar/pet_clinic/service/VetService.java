package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.VetRequestDto;
import com.sagar.pet_clinic.repository.VetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VetService {

    private final VetRepository vetRepository;
    private final VetMapper vetMapper;

    public VetService(VetRepository vetRepository, VetMapper vetMapper) {
        this.vetRepository = vetRepository;
        this.vetMapper = vetMapper;
    }

    // CREATE VET
    public ResponseEntity<?> createVet(VetRequestDto requestDto) {
        try {
            var vet = vetMapper.toVet(requestDto);
            var savedVet = vetRepository.save(vet);
            return ResponseEntity.status(HttpStatus.CREATED).body(vetMapper.toVetResponseDto(savedVet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // UPDATE VET
    public ResponseEntity<?> updateVet(VetRequestDto requestDto, Integer id) {
        try {
            // Find the existing owner by ID
            var existingVet = vetRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Vet not found"));
            if (requestDto.name() != null) {
                existingVet.setName(requestDto.name());
            }
            if (requestDto.address()!= null) {
                existingVet.setAddress(requestDto.address());
            }
            if (requestDto.specialization() != null) {
                existingVet.setSpecialization(requestDto.specialization());
            }

            var updatedVet = vetRepository.save(existingVet);

            return ResponseEntity.status(HttpStatus.OK).body(vetMapper.toVetResponseDto(updatedVet));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET VET
    public ResponseEntity<?> getVet(Integer id) {
        try {
            var existingVet = vetRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Vet not found"));
            return ResponseEntity.status(HttpStatus.OK).body(existingVet);
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // DELETE VET
    public ResponseEntity<?> deleteVet(Integer id) {
        try {
            var existingVet = vetRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Vet not found"));
            vetRepository.delete(existingVet);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET ALL VETS
    public ResponseEntity<?> getAllVets() {
        try {
            var allVets = vetRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(allVets);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET ALL APPOINTMENT OF VET
    public ResponseEntity<?> getAllAppointmentOfVet(Integer id) {
        try {
            var existingVet = vetRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Vet not found"));
            return ResponseEntity.status(HttpStatus.OK).body(existingVet.getAppointments());
        } catch ( IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
