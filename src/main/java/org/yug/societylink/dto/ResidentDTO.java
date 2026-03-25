package org.yug.societylink.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ResidentDTO {

    private Long id;

    @NotBlank(message = "NAME CANNOT BE BLANK")
    private String name;

    @NotBlank(message = "FLAT NUMBER CANNOT BE BLANK")
    private String flatNumber;

    @Size(min = 10, max = 10, message = "MOBILE NUMBER MUST BE EXACTLY 10 DIGITS")
    private String contactNumber;

    @Email(message = "INVALID EMAIL FORMAT")
    private String email;

    @NotBlank(message = "PASSWORD CANNOT BE BLANK")
    @Size(min = 6, message = "PASSWORD MUST BE AT LEAST 6 CHARACTERS")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // 1. REQUIRED: The No-Arguments Constructor for Jackson
    public ResidentDTO() {
    }

    // 2. Your custom constructor (used when returning data from the Service)
    public ResidentDTO(Long id, String name, String flatNumber, String contactNumber, String email) {
        this.id = id;
        this.name = name;
        this.flatNumber = flatNumber;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // --- GETTERS (Required for Jackson to generate JSON and for your Service layer) ---
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getFlatNumber() { return flatNumber; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public String getPassword() { return password; } // Service layer needs this!

    // --- SETTERS (Required for Jackson to read incoming JSON) ---
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}