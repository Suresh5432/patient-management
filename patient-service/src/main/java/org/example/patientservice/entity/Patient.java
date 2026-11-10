package org.example.patientservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    private UUID id;

    private String name;

    @Column(nullable = false)
    private String email;

    private String address;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    private LocalDate registrationDate;

}
