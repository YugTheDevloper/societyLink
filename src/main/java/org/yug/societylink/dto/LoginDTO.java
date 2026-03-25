package org.yug.societylink.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "EMAIL CAN'T BE BLANK!")
    @Email(message = "PLEASE ENTER A VALID EMAIL!")
    private String email;
    @NotBlank(message = "PASSWORD CAN'T BE BLANK")
    private String password;
}
