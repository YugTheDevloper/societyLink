    package org.yug.societylink.model;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Resident {


        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        @NotBlank(message = "NAME IS REQURIED !")
        @Size(min = 3, message = "NAME MUST BE AT LEAST 3 CHARACTERS!")
        private String name;

        @Column
        @NotBlank(message = "FLAT NUMBER IS REQUIRED!")
        private String flatNumber;

        @Column
        @NotBlank(message = "CONTACT NUMBER IS REQUIRED!")
        @Size(min = 10, max=10,message = "MOBILE NUMBER MUST BE EXACTLY 10 DIGITS ")
        private String contactNumber;

        @Column(unique = true, nullable = false)
        @NotBlank(message = "EMAIL IS REQUIRED!")
        @Email(message="INVALID EMAIL FORMAT!")
        private String email;

        @Column
        @NotBlank(message = "PASSWORD IS REQUIRED")
        @Size(min=6,message = "PASSWORD MUST BE AT LEAST 6 CHARACTERS")
        private String password;

        @NotBlank(message = "ROLE IS REQUIRED!")
        private String roles;

        @OneToMany(mappedBy = "resident" , cascade=CascadeType.MERGE)
        List<Complaint> complaints;

    }
