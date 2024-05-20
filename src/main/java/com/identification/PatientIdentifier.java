package com.identification;

import java.util.HashMap;
import java.util.Map;

public class PatientIdentifier {
    private Map<String, PatientRecord> patientDatabase;

    public PatientIdentifier() {
        patientDatabase = new HashMap<>();
        // Assume patientDatabase is populated with patient records from the hospital database
    }

    // Method to match the patient ID with existing records
    public boolean matchPatientId(String patientId) {
        return patientDatabase.containsKey(patientId);
    }

    // Method to get the patient record based on patient ID
    public PatientRecord getPatientRecord(String patientId) {
        return patientDatabase.get(patientId);
    }

    // Method to add patient record (for initialization)
    public void addPatientRecord(PatientRecord record) {
        patientDatabase.put(record.getPatientId(), record);
    }
}