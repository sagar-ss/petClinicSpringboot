package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.OwnerRequestDto;
import com.sagar.pet_clinic.dto.OwnerResponseDto;
import com.sagar.pet_clinic.dto.OwnerUpdateRequestDto;
import com.sagar.pet_clinic.dto.OwnerUpdateResponseDto;
import com.sagar.pet_clinic.model.Owner;
import com.sagar.pet_clinic.model.Pet;
import com.sagar.pet_clinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OwnerService {


    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public OwnerService(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    // CREATE OWNER
    public OwnerResponseDto createOwner(OwnerRequestDto requestDto) {
        var owner = ownerMapper.toOwner(requestDto);
        var savedOwner = ownerRepository.save(owner);
        return ownerMapper.toOwnerResponseDto(savedOwner);
    }

    // UPDATE OWNER
    public OwnerUpdateResponseDto updateOwner(OwnerUpdateRequestDto requestDto, Integer id) {
        // Find the existing owner by ID
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner_id: " + id));
        if (!requestDto.firstName().isEmpty()) {
            existingOwner.setFirstName(requestDto.firstName());
        }
        if (!requestDto.lastName().isEmpty()) {
            existingOwner.setLastName(requestDto.lastName());
        }
        if (!requestDto.email().isEmpty()) {
            existingOwner.setEmail(requestDto.email());
        }
        if (!requestDto.telephone().isEmpty()) {
            existingOwner.setTelephone(requestDto.telephone());
        }
        if (!requestDto.address().isEmpty()) {
            existingOwner.setAddress(requestDto.address());
        }
        if (!requestDto.city().isEmpty()){
            existingOwner.setCity(requestDto.city());
        }
            Owner updatedOwner = ownerRepository.save(existingOwner);
        return ownerMapper.toOwnerUpdateResponseDto(updatedOwner);

    }

    // GET OWNER
    public OwnerResponseDto getOwner(Integer id) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner_id: " + id));
        return ownerMapper.toOwnerResponseDto(existingOwner);
    }

    // GET ALL OWNERS

    public List<OwnerResponseDto> getAllOwners() {

        List<Owner> existingOwner = ownerRepository.findAll();
        return existingOwner.stream().map(ownerMapper::toOwnerResponseDto).collect(Collectors.toList());
    }

    // DELETE OWNER
    public void deleteOwner(Integer id) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner_id: " + id));
        ownerRepository.deleteById(id);

    }

    // FIND ALL PETS OF A OWNER
    public List<Pet> findAllPets(Integer id) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid owner_id"));
        return existingOwner.getPets();

    }

}
