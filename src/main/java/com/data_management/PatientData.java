package com.data_management;

public class PatientData {
    private int patientId;
    private double currentHeartRate;

    public PatientData(int patientId) {
        this.patientId = patientId;
    }

    public int getPatientId() {
        return patientId;
    }

    public double getCurrentHeartRate() {
        return currentHeartRate;
    }

    public void setCurrentHeartRate(double currentHeartRate) {
        this.currentHeartRate = currentHeartRate;
    }

}
