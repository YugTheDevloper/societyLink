package org.yug.societylink.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComplaintAdminResponseDTO {

    private Long caseId;

    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String status;

    // Resident Details (ModelMapper extracts these automatically!)
    private String name;
    private String contactNumber;
    private String flatNumber;
}
