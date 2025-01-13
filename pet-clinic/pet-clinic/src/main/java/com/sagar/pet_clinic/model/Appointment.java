package com.sagar.pet_clinic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    // valid format ex: 2025-01-15T10:30
    private LocalDateTime appointmentDateAndTime;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    @JsonBackReference
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    @JsonBackReference
    private Vet vet;


    @PrePersist
    private void setDefaultStatus() {
        if (status == null) {
            status = AppointmentStatus.PENDING; // Default value
        }
    }
}
