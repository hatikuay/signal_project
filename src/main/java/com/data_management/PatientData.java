package com.data_management;

import java.util.Map;

public class PatientData {
    private int patientId;
    private Map<String, Double> metrics;
    private long timestamp;

    public PatientData(int patientId, Map<String, Double> metrics, long timestamp) {
        this.patientId = patientId;
        this.metrics = metrics;
        this.timestamp = timestamp;
    }

    public int getPatientId() {
        return patientId;
    }

    public Map<String, Double> getMetrics() {
        return metrics;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
