package org.yug.societylink.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yug.societylink.dto.LoginDTO;
import org.yug.societylink.dto.ResidentDTO;
import org.yug.societylink.dto.UpdateDTO;
import org.yug.societylink.model.Complaint;
import org.yug.societylink.model.Resident;
import org.yug.societylink.service.ComplaintService;
import org.yug.societylink.service.ResidentService;
import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;



    @PostMapping("/save")
    public ResponseEntity<ResidentDTO> saveResident(@Valid @RequestBody ResidentDTO residentDTO) {
        ResidentDTO saved = residentService.registerResident(residentDTO);
        // Returns 201 Created
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResidentDTO> loginResident(@Valid @RequestBody LoginDTO loginDTO) {
        // If login fails, Spring automatically jumps to GlobalExceptionHandler
        ResidentDTO authResident = residentService.loginResident(loginDTO);
        // Returns 200 OK
        return ResponseEntity.ok(authResident);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ResidentDTO>> getAllResidents(@RequestParam(required = false,defaultValue = "1") int pageNumber,
                                             @RequestParam(required = false,defaultValue = "5") int pageSize,
                                             @RequestParam(required = false,defaultValue = "id") String sortBy) {

        Page<ResidentDTO> residentDTOPage= residentService.getAllResidents(pageNumber-1,pageSize,sortBy);
        return ResponseEntity.ok(residentDTOPage);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResidentDTO> updateResident( @PathVariable Long id ,
                                                      @Valid @RequestBody UpdateDTO updateDTO){



        return ResponseEntity.ok(residentService.updateResident(id,updateDTO));
    }


}

