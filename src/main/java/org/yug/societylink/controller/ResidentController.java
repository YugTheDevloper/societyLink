package org.yug.societylink.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.yug.societylink.dto.ResidentDTO;
import org.yug.societylink.dto.UpdateDTO;
import org.yug.societylink.security.ResidentDetails;
import org.yug.societylink.service.ResidentService;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ResidentDTO>> getAllResidents(@RequestParam(required = false,defaultValue = "0") int pageNumber,
                                             @RequestParam(required = false,defaultValue = "5") int pageSize,
                                             @RequestParam(required = false,defaultValue = "id") String sortBy) {

        Page<ResidentDTO> residentDTOPage= residentService.getAllResidents(pageNumber,pageSize,sortBy);
        return ResponseEntity.ok(residentDTOPage);
    }

    @PatchMapping("/update/")
    @PreAuthorize("hasRole('RESIDENT')")
    public ResponseEntity<ResidentDTO> updateResident(@Valid @RequestBody UpdateDTO updateDTO){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        ResidentDetails loggedInResident=(ResidentDetails) authentication.getPrincipal();
        return ResponseEntity.ok(residentService.updateResident(loggedInResident.getUsername(),updateDTO));
    }


}

