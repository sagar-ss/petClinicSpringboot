package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.VetRequestDto;
import com.sagar.pet_clinic.dto.VetResponseDto;
import com.sagar.pet_clinic.model.Appointment;
import com.sagar.pet_clinic.repository.VetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetService {

    private final VetRepository vetRepository;
    private final VetMapper vetMapper;

    public VetService(VetRepository vetRepository, VetMapper vetMapper) {
        this.vetRepository = vetRepository;
        this.vetMapper = vetMapper;
    }

    // CREATE VET
    public VetResponseDto createVet(VetRequestDto requestDto) {

            var vet = vetMapper.toVet(requestDto);
            var savedVet = vetRepository.save(vet);
            return vetMapper.toVetResponseDto(savedVet);
    }

    // UPDATE VET
    public VetResponseDto updateVet(VetRequestDto requestDto, Integer id) {
            // Find the existing owner by ID
            var existingVet = vetRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Vet not found"));
            if (!requestDto.name().isEmpty()) {
                existingVet.setName(requestDto.name());
            }
            if (!requestDto.address().isEmpty()) {
                existingVet.setAddress(requestDto.address());
            }
            if (!requestDto.specialization().isEmpty()) {
                existingVet.setSpecialization(requestDto.specialization());
            }

            var updatedVet = vetRepository.save(existingVet);

            return vetMapper.toVetResponseDto(updatedVet);
    }

    // GET VET
    public VetResponseDto getVet(Integer id) {
            var existingVet = vetRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Vet not found"));
            return vetMapper.toVetResponseDto(existingVet);
    }

    // DELETE VET
    public void deleteVet(Integer id) {
            var existingVet = vetRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Vet not found"));
            vetRepository.delete(existingVet);

    }

    // GET ALL VETS
    public List<VetResponseDto> getAllVets() {
            var allVets = vetRepository.findAll();
            return allVets.stream().map(vetMapper :: toVetResponseDto).collect(Collectors.toList());
    }

    // GET ALL APPOINTMENT OF VET
    public List<Appointment> getAllAppointmentOfVet(Integer id) {
            var existingVet = vetRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Vet not found"));
            return existingVet.getAppointments();

    }
}
