package org.example.patientservice.repo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.patientservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);

    Optional<Patient> findPatientById(UUID id);

    boolean existsByEmailAndIdNot(String email, UUID id);
}
