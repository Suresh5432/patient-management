package org.example.patientservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.patientservice.dto.PatientRequest;
import org.example.patientservice.entity.Patient;
import org.example.patientservice.service.PatientService;
import org.example.patientservice.dto.PatientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PatientResponse> getAllPatients(){
        return patientService.getPatients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponse addPatient(@Valid @RequestBody PatientRequest request){
        return patientService.createPatient(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientResponse updatePatient(@PathVariable UUID id,
                                         @Valid @RequestBody PatientRequest request){
        return patientService.updatePatient(id,request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
    }
}
