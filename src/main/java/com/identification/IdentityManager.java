package com.identification;

public class IdentityManager {
    private PatientIdentifier patientIdentifier;

    public IdentityManager(PatientIdentifier patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    // Method to handle incoming data and link it to the correct patient record
    public PatientRecord linkDataToPatient(String patientId) throws PatientNotFoundException {
        if (patientIdentifier.matchPatientId(patientId)) {
            return patientIdentifier.getPatientRecord(patientId);
        } else {
            throw new PatientNotFoundException("Patient ID not found: " + patientId);
        }
    }

    // Method to resolve discrepancies in patient ID matching
    public void handleDiscrepancies(String patientId, String correctPatientId) {
        // Logic to handle discrepancies, such as updating the patient ID in the database
        PatientRecord record = patientIdentifier.getPatientRecord(patientId);
        if (record != null) {
            patientIdentifier.addPatientRecord(new PatientRecord(correctPatientId, record.getName(), record.getDateOfBirth(), record.getMedicalHistory()));
            // Optionally, remove the old record with the incorrect patient ID
        }
    }
}

class PatientNotFoundException extends Exception {
    public PatientNotFoundException(String message) {
        super(message);
    }
}