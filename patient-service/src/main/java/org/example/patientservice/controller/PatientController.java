package org.example.patientservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.patientservice.dto.PatientRequest;
import org.example.patientservice.service.PatientService;
import org.example.patientservice.dto.PatientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
@Tag(name="patient",description = "API for managing Patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Getting the patients")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientResponse> getAllPatients(){
        return patientService.getPatients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = " Saving a patient")
    public PatientResponse addPatient(@Valid @RequestBody PatientRequest request){
        return patientService.createPatient(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a patient")
    public PatientResponse updatePatient(@PathVariable UUID id,
                                         @Valid @RequestBody PatientRequest request){
        return patientService.updatePatient(id,request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a patient")
    public void deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
    }
}
