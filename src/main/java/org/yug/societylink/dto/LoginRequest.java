package org.yug.societylink.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "EMAIL FIELD CAN'T BE BLANK")
    public String email;
    @NotBlank(message = "PASSWORD FIELD CAN'T BE BLANK")
    public String password;


}
