package org.yug.societylink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;
    @Column
    private String title;
    @Column(columnDefinition = "TEXT") //THIS ALLOW FOR VERY LONG DESCRIPTION
    private String description;

    @Column(nullable = false)
    private String status="PENDING";
    @Column
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="resident_id",nullable = false) //ENSURE EVERY COMPLAINT MUST HAVE A RESIDENT
    @JsonIgnore
    private Resident resident;

    @PrePersist //  THIS EXECUTES BEFORE INSERT STATEMENT
    protected void onCreated(){
        this.createdAt=LocalDateTime.now();
    }
}
