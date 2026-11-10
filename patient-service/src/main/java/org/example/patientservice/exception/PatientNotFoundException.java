package org.example.patientservice.exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String s) {
        super(s);
    }
}
