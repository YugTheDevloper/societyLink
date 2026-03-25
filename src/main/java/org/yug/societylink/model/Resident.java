    package org.yug.societylink.model;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;

    import java.util.List;

    @Entity

    public class Resident {

        /* LISTEN EVEN I NOT NEED TO MARK EACH FIELD WITH @COLUMN AS SPRING BOOT
        AUTOMATICALLY UNDERSTANDS THAT ALL PRIVATE FIELDS ARE RELATED TO DATABASE
        TABLE
         */

        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        @NotBlank(message = "NAME CAN'T BE NULL!")
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

        @OneToMany(mappedBy = "resident" , cascade=CascadeType.MERGE)
        List<Complaint> complaints;
    public Resident(){};
    /*
    EMPTY CONSTRUCTOR SO THAT SPRING CAN INITIALIZE THE FIELDS
      (HIBERNATE PROXY)
      * */

      public Resident(Long id , String name,String flatNumber,String contactNumber,String email,String password){
          this.id=id;
          this.name=name;
          this.flatNumber=flatNumber;
          this.contactNumber=contactNumber;
          this.email=email;
          this.password=password;
      }

      /*
      THIS PARAMETERIZED CONSTRUCTOR IS FOR US SO THAT WE CAN INSTANTIATE
      THE FIELDS
       */

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFlatNumber() {
            return flatNumber;
        }

        public void setFlatNumber(String flatNumber) {
            this.flatNumber = flatNumber;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
