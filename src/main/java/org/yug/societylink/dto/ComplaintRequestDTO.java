package org.yug.societylink.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ComplaintRequestDTO {

    @NotBlank(message = "PLEASE ENTER A VALID ID!")
    private Long resident_id;
    @NotBlank(message = "PLEASE ENTER THE TITLE!")
    private String title;
    @NotBlank(message = "PLEASE PROVIDE THE DESCRIPTION OF COMPLAINT!")

    private String description;




}
