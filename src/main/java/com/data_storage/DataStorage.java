package com.data_storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {
    private Map<String, List<PatientData>> dataStorage;
    private Map<String, String> userRoles;

    public DataStorage() {
        dataStorage = new HashMap<>();
        userRoles = new HashMap<>();
    }

    public void storeData(PatientData data) {
        String patientId = data.getPatientId();
        if (!dataStorage.containsKey(patientId)) {
            dataStorage.put(patientId, new ArrayList<>());
        }
        dataStorage.get(patientId).add(data);
    }

    public List<PatientData> retrieveData(String patientId, String userId) throws UnauthorizedAccessException {
        if (isAuthorized(userId, patientId)) {
            return dataStorage.getOrDefault(patientId, new ArrayList<>());
        } else {
            throw new UnauthorizedAccessException("User not authorized to access this data");
        }
    }

    public void deleteData(String patientId, String userId) throws UnauthorizedAccessException {
        if (isAuthorized(userId, patientId)) {
            dataStorage.remove(patientId);
        } else {
            throw new UnauthorizedAccessException("User not authorized to delete this data");
        }
    }

    public void addUserRole(String userId, String role) {
        userRoles.put(userId, role);
    }

    private boolean isAuthorized(String userId, String patientId) {
        String role = userRoles.get(userId);
        if (role == null)
            return false;

        // Example role-based access control
        if (role.equals("doctor") || role.equals("nurse")) {
            return true;
        } else if (role.equals("patient") && userId.equals(patientId)) {
            return true;
        } else {
            return false;
        }
    }
}
