package org.yug.societylink.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDTO {
    private String name;
    private String flatNumber;
    //IT ENSURES THAT IF WE ARE SENDING THIS DATA THEN IT MUST BE OF STANDARD SIZE
    @Pattern(regexp = "^[0-9]{10}$", message = "CONTACT NUMBER MUST BE 10 DIGITS")
    private String contactNumber;
}
