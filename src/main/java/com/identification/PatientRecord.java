package com.identification;

import java.util.Date;
import java.util.List;

public class PatientRecord {
    private String patientId;
    private String name;
    private Date dateOfBirth;
    private List<String> medicalHistory;

    // Constructor
    public PatientRecord(String patientId, String name, Date dateOfBirth, List<String> medicalHistory) {
        this.patientId = patientId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.medicalHistory = medicalHistory;
    }

    // Getters and Setters for each attribute
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(List<String> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
