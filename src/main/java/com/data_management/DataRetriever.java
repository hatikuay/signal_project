package com.data_management;

public class DataRetriever {
    private DataStorage dataStorage;

    public DataRetriever(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public PatientData retrievePatientData(int patientId) {
        return dataStorage.retrieveData(patientId);
    }

}
