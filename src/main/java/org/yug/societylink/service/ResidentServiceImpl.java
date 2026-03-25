package org.yug.societylink.service;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yug.societylink.dto.LoginDTO;
import org.yug.societylink.dto.ResidentDTO;
import org.yug.societylink.dto.UpdateDTO;
import org.yug.societylink.exception.BadCredentials;
import org.yug.societylink.exception.UserNotFoundException;
import org.yug.societylink.model.Resident;
import org.yug.societylink.repository.ResidentRepository;

import org.springframework.data.domain.Pageable;

@Service

public class ResidentServiceImpl implements ResidentService{


    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private ModelMapper modelMapper;


    // --- METHOD 1: REGISTER ---
    @Override
    public ResidentDTO registerResident(ResidentDTO residentDTO) {
        // Business Logic: (Future) Check if email already exists?
        // For now, just save to DB.

        Resident resident = modelMapper.map(residentDTO,Resident.class);
        Resident savedResident=residentRepository.save(resident);
        return modelMapper.map(savedResident,ResidentDTO.class);
    }

    // --- METHOD 2: LOGIN ---
    @Override
    public ResidentDTO loginResident(LoginDTO loginDTO) {
        // 1. Find the user by Email (Using the custom method we made!)
        Resident resident = residentRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new UserNotFoundException("USER NOT FOUND WITH EMAIL:"+loginDTO.getEmail()));


            // 2. Check Password
            // Note: In real life, we use BCrypt for hashing, never plain text.
            if (resident.getPassword().equals(loginDTO.getPassword())) {

                //for now i manually created dto
                return modelMapper.map(resident,ResidentDTO.class); // Success: Return the user
            }else{
                throw new BadCredentials("ENTER A VALID PASSWORD ");
        }


    }

    // --- METHOD 3: GET ALL ---
    @Override
    public Page<ResidentDTO> getAllResidents(int pageNumber, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageSize,pageNumber, Sort.by(sortBy));
        Page<Resident> residentPage=residentRepository.findAll(pageable);

        Page<ResidentDTO> residentDto=residentPage.map((Resident resident)-> new ResidentDTO(resident.getId(),resident.getName(),resident.getFlatNumber(),resident.getContactNumber(),resident.getEmail()));


        return residentDto;
    }

    @Override
    public ResidentDTO updateResident(Long id , UpdateDTO updateDTO){

        Resident existingResident = residentRepository.findById(id).orElseThrow(()->new UserNotFoundException("USER NOT FOUND!"));
        if(updateDTO.getName()!=null && !updateDTO.getName().trim().isEmpty())
            existingResident.setName(updateDTO.getName());

        if(updateDTO.getContactNumber()!=null && !updateDTO.getContactNumber().trim().isEmpty())
            existingResident.setContactNumber(updateDTO.getContactNumber());

        if(updateDTO.getFlatNumber()!=null && !updateDTO.getFlatNumber().trim().isEmpty())
            existingResident.setFlatNumber(updateDTO.getFlatNumber());

        Resident updatedResident = residentRepository.save(existingResident);
        return modelMapper.map(updatedResident,ResidentDTO.class);
    }

}
