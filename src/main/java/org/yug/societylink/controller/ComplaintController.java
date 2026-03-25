package org.yug.societylink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yug.societylink.dto.ComplaintAdminResponseDTO;
import org.yug.societylink.dto.ComplaintRequestDTO;
import org.yug.societylink.dto.ComplaintResponseDTO;
import org.yug.societylink.model.Complaint;
import org.yug.societylink.service.ComplaintService;

import java.util.List;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/resident/{residentId}")
    public ResponseEntity<ComplaintResponseDTO> saveComplaint(@PathVariable Long residentId,
                                                              @RequestBody ComplaintRequestDTO complaintRequestDTO){

        return new ResponseEntity<>(complaintService.saveComplaint(residentId, complaintRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<ComplaintResponseDTO>> getComplaintForUser(@PathVariable Long residentId){

        return new ResponseEntity<>(complaintService.getComplaintByResidentId(residentId),HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<ComplaintAdminResponseDTO>> getComplaintForAdmin(@RequestParam(required = false,defaultValue = "1") int page,
                                                                                @RequestParam(required = false,defaultValue = "5") int size,
                                                                                @RequestParam(required = false,defaultValue = "createdAt") String sortBy){

        return new ResponseEntity<>(complaintService.getComplaintsForAdmin(page+1,size,sortBy),HttpStatus.OK);
    }

}
