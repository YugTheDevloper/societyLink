package org.yug.societylink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.yug.societylink.dto.ComplaintAdminResponseDTO;
import org.yug.societylink.dto.ComplaintRequestDTO;
import org.yug.societylink.dto.ComplaintResponseDTO;
import org.yug.societylink.model.Complaint;
import org.yug.societylink.security.ResidentDetails;
import org.yug.societylink.service.ComplaintService;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/resident/")
    @PreAuthorize("hasRole('RESIDNET')")
    public ResponseEntity<ComplaintResponseDTO> saveComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResidentDetails loggedInResident=(ResidentDetails) authentication.getPrincipal();
        return new ResponseEntity<>(complaintService.saveComplaint(loggedInResident.getUsername(), complaintRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/my-complaints")
    @PreAuthorize("hasRole('RESIDENT')")

    public ResponseEntity<List<ComplaintResponseDTO>> getComplaintForResident(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResidentDetails loggedInResident=(ResidentDetails) authentication.getPrincipal();
        return new ResponseEntity<>(complaintService.getComplaintByResidentEmail(loggedInResident.getUsername()),HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ComplaintAdminResponseDTO>> getComplaintForAdmin(@RequestParam(required = false,defaultValue = "0") int page,
                                                                                @RequestParam(required = false,defaultValue = "5") int size,
                                                                                @RequestParam(required = false,defaultValue = "createdAt") String sortBy){

        return new ResponseEntity<>(complaintService.getComplaintsForAdmin(page+1,size,sortBy),HttpStatus.OK);
    }

}
