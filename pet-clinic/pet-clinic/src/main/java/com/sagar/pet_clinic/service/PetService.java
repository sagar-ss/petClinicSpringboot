package com.sagar.pet_clinic.service;

import com.sagar.pet_clinic.dto.PetRequestDto;
import com.sagar.pet_clinic.dto.PetResponseDto;
import com.sagar.pet_clinic.exception.ResourceNotFoundException;
import com.sagar.pet_clinic.model.Owner;
import com.sagar.pet_clinic.model.Pet;
import com.sagar.pet_clinic.repository.OwnerRepository;
import com.sagar.pet_clinic.repository.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PetResponseDto createPet(PetRequestDto requestDto) {

            Owner existingOwner = ownerRepository.findById(requestDto.owner_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid owner_id: "+requestDto.owner_id()));
            var pet = petMapper.toPet(requestDto);
            existingOwner.getPets().add(pet);
            var savedPet = petRepository.save(pet);
            return petMapper.toPetResponseDto(savedPet);
    }

    // UPDATE PET
    public PetResponseDto updatePet(PetRequestDto requestDto, Integer id) {

            var existingPet = petRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid pet_id: "+id));

            if(!requestDto.name().isEmpty()){
                existingPet.setName(requestDto.name());
            }
            if(requestDto.birthDate()!=null){
                existingPet.setBirthDate(requestDto.birthDate());
            }
            var savedPet = petRepository.save(existingPet);
            return petMapper.toPetResponseDto(savedPet);
    }


    // DELETE PET
    public void deletePet(Integer id) {
            var existingPet = petRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid pet_id: "+id));

            existingPet.getOwner().getPets().remove(existingPet);
            petRepository.delete(existingPet);
    }

    // GET PET
    public PetResponseDto getPet(Integer id){
            var existingPet = petRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid pet_id: "+ id));

            return petMapper.toPetResponseDto(existingPet);
    }


    public List<Pet> findAllPetsOfOwner(Integer ownerId) {
            Owner existingOwner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid owner_id: "+ownerId));

            return existingOwner.getPets();
    }
}
