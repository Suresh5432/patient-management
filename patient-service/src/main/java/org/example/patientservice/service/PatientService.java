package org.example.patientservice.service;

import lombok.RequiredArgsConstructor;
import org.example.patientservice.dto.PatientRequest;
import org.example.patientservice.entity.Patient;
import org.example.patientservice.exception.EmailAlreadyExistsException;
import org.example.patientservice.exception.PatientNotFoundException;
import org.example.patientservice.repo.PatientRepository;
import org.example.patientservice.dto.PatientResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;


    public List<PatientResponse> getPatients(){
        return patientRepository.findAll().stream().map(patient->
                PatientResponse.builder()
                        .id(patient.getId().toString())
                        .name(patient.getName())
                        .email(patient.getEmail())
                        .address(patient.getAddress())
                        .dateOfBirth(patient.getDateOfBirth().toString())
                        .build()
                ).toList();
    }

    public PatientResponse createPatient(PatientRequest patientRequest){
        if(patientRepository.existsByEmail(patientRequest.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists "+ patientRequest.getEmail());
        }
        Patient patient= Patient.builder()
                .id(UUID.randomUUID())
                .name(patientRequest.getName())
                .email(patientRequest.getEmail())
                .address(patientRequest.getAddress())
                .dateOfBirth(LocalDate.parse(patientRequest.getDateOfBirth()))
                .registrationDate(LocalDate.parse(patientRequest.getRegistrationDate()))
                .build();
        patientRepository.save(patient);
        return PatientResponse.builder()
                .id(patient.getId().toString())
                .name(patient.getName())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .dateOfBirth(patient.getDateOfBirth().toString())
                .build();
    }

    public PatientResponse updatePatient(UUID id,PatientRequest request) {
        Patient patient= patientRepository.findPatientById(id).orElseThrow(()->
                new PatientNotFoundException("Patient is not found with id "+id));
        if(patientRepository.existsByEmailAndIdNot(request.getEmail(),id)){
            throw new EmailAlreadyExistsException("A patient with this email already exists "+ request.getEmail());
        }
        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setAddress(request.getAddress());
        patient.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        patient.setRegistrationDate(LocalDate.parse(request.getRegistrationDate()));
        patientRepository.save(patient);
        return PatientResponse.builder()
                .id(patient.getId().toString())
                .name(patient.getName())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .dateOfBirth(patient.getDateOfBirth().toString())
                .build();
    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }
}
