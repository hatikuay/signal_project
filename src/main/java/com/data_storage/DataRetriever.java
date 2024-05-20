package com.data_storage;

import java.util.List;

public class DataRetriever {
    private DataStorage dataStorage;

    public DataRetriever(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public List<PatientData> fetchPatientData(String patientId, String userId) throws UnauthorizedAccessException {
        return dataStorage.retrieveData(patientId, userId);
    }

    public List<PatientData> fetchHistoricalData(String patientId, String userId) throws UnauthorizedAccessException {
        return dataStorage.retrieveData(patientId, userId);
    }
}
