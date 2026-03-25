package org.yug.societylink.dto;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class  ComplaintResponseDTO {

    private Long caseId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String status;
}
