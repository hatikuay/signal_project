package com.data_management;

import java.util.concurrent.ConcurrentHashMap;

public class DataStorage {
  private ConcurrentHashMap<Integer, PatientData> storage = new ConcurrentHashMap<>();

  public synchronized void storageData(PatientData data) {
    storage.put(data.getPatientId(), data);
    System.out.println("Data stored for patient ID: " + data.getPatientId());
  }

  public synchronized PatientData retrieveData(int patientId) {
    System.out.println("Retrieving data for patient ID: " + patientId);
    return storage.get(patientId);
  }

  public synchronized void deleteData(int patientId) {
    if (storage.containsKey(patientId)) {
      storage.remove(patientId);
      System.out.println("Data deleted for patient ID: " + patientId);
    } else {
      System.out.println("No data found for patient ID: " + patientId);
    }
  }

}
