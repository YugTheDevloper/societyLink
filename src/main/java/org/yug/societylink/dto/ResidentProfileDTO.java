package org.yug.societylink.dto;

import lombok.Data;

import java.util.List;
@Data
public class ResidentProfileDTO {

    private String name;
    private String email;
    private String contactNumber;
    private List<ComplaintResponseDTO> complaints;



}
