package com.data_management;

public class DataRetriever {
    private DataStorage dataStorage;

    public DataRetriever(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public PatientData retrievePatientData(int patientId) {
        Patient patient = dataStorage.getPatient(patientId);
        if (patient != null) {
            PatientData patientData = new PatientData(patientId);
            patientData.setCurrentHeartRate(patient.getCurrentHeartRate());
            return patientData;
        }
        return null;
    }

}
