package com.alerts;

public class AlertManager {

    private static AlertManager instance = new AlertManager();

    public static AlertManager getInstance() {
        return instance;
    }

    public void sendAlert(Alert alert) {
        System.out.println("Alert sent for patient " + alert.getPatientId() + ": " + alert.getCondition() + " at "
                + alert.getTimestamp());
    }
}
