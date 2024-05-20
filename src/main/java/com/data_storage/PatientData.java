package com.data_storage;
import java.util.Map;


public class PatientData {
    private String patientId;
    private Map<String, Double> metrics;
    private long timestamp;

    public PatientData(String patientId, Map<String, Double> metrics, long timestamp) {
        this.patientId = patientId;
        this.metrics = metrics;
        this.timestamp = timestamp;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Map<String, Double> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<String, Double> metrics) {
        this.metrics = metrics;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}