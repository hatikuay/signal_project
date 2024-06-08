package com.alerts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data_storage.DataStorage;
import com.data_storage.PatientData;

public class AlertGenerator {

    private DataStorage dataStorage;
    private Map<Integer, Map<String, Double>> patientAlertThresholds;

    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        this.patientAlertThresholds = new HashMap<>();
    }

    @SuppressWarnings("unlikely-arg-type")
    public void evaluateData(PatientData patient) {
        String patientId = patient.getPatientId();
        if (!patientAlertThresholds.containsKey(patientId)) {
            return;
        }

        Map<String, Double> thresholds = patientAlertThresholds.get(patientId);
        for (Map.Entry<String, Double> entry : patient.getMetrics().entrySet()) {
            String metric = entry.getKey();
            double value = entry.getValue();
            if (thresholds.containsKey(metric) && value > thresholds.get(metric)) {
                triggerAlert(new Alert(patientId, metric, System.currentTimeMillis()));
            }
        }
    }

    public void setThreshold(int patientId, String condition, double value) {
        if (!patientAlertThresholds.containsKey(patientId)) {
            patientAlertThresholds.put(patientId, new HashMap<>());
        }
        patientAlertThresholds.get(patientId).put(condition, value);
    }

    /*Functionality Requirements:
    ● Trend Alert: Trigger an alert if the patient's blood pressure (systolic or diastolic) shows a
    consistent increase or decrease across three consecutive readings where each reading
    changes by more than 10 mmHg from the last.
    ● Critical Threshold Alert: Trigger an alert if the systolic blood pressure exceeds 180
    mmHg or drops below 90 mmHg, or if diastolic blood pressure exceeds 120 mmHg or
    drops below 60 mmHg.*/
    private void checkBloodPressureAlerts(String patientId, Map<String, Double> metrics) {
        if (metrics.containsKey("systolic") && metrics.containsKey("diastolic")) {
            double systolic = metrics.get("SystolicPressure");
            double diastolic = metrics.get("DiastolicPressure");

            if (systolic > 180 || systolic < 90 || diastolic > 120 || diastolic < 60) {
                triggerAlert(new Alert(patientId, "Critical Blood Pressure", System.currentTimeMillis()));
            }

            /*Trigger Condition:
            ● Test for Increasing Trend: Verify an alert is triggered when three consecutive blood
            pressure readings increase by more than 10 mmHg each.
            ● Test for Decreasing Trend: Verify an alert is triggered when three consecutive blood
            pressure readings decrease by more than 10 mmHg each.
            ● Test for Critical Thresholds: Ensure alerts are triggered for readings above 180/120
            mmHg and below 90/60 mmHg respectively*/
            List<Double> systolicReadings = dataStorage.getRecentReadings(patientId, "systolic", 3);
            if (systolicReadings.size() == 3 && isTrend(systolicReadings, 10)) {
                triggerAlert(new Alert(patientId, "Blood Pressure Increasing Trend", System.currentTimeMillis()));
            }
        }

    }

    /*Functionality Requirements:
        ● Low Saturation Alert: Trigger an alert if the blood oxygen saturation level falls below
        92%.
        ● Rapid Drop Alert: Trigger an alert if the blood oxygen saturation level drops by 5% or
        more within a 10-minute interval.*/
    private void checkBloodSaturationAlerts(String patientId, Map<String, Double> metrics) {
        if (metrics.containsKey("Saturation")) {
            double saturation = metrics.get("Saturation");

            if (saturation < 92) {
                triggerAlert(new Alert(patientId, "Low Blood Oxygen Saturation", System.currentTimeMillis()));
            }

            /*Trigger Condition:
            ● Test for Low Saturation: Ensure an alert is triggered when saturation falls below 92%.
            ● Test for Rapid Drop: Ensure an alert is triggered when there is a drop of 5% or more
            within 10 minutes.*/
            List<Double> saturationReadings = dataStorage.getRecentReadings(patientId, "Saturation", 10);
            if (saturationReadings.size() >= 2) {
                double latest = saturationReadings.get(saturationReadings.size() - 1);
                double previous = saturationReadings.get(saturationReadings.size() - 2);
                if (previous - latest >= 5) {
                    triggerAlert(new Alert(patientId, "Rapid Drop in Blood Oxygen Saturation", System.currentTimeMillis()));
                }
            }
        }

    }

    /*3. Combined Alert: Hypotensive Hypoxemia Alert
    This alert type, called the "Hypotensive Hypoxemia Alert," will trigger based on combined low
    blood pressure and low blood oxygen saturation levels. This condition is particularly dangerous
    because it indicates potential organ damage or severe infection risks, among other serious
    health concerns.
    Functionality Requirements:
    ● Trigger Condition: The alert should trigger when both:
    ○ Systolic blood pressure is below 90 mmHg.
    ○ Blood oxygen saturation falls below 92%.
    Rationale: This combination can be indicative of shock or severe respiratory issues, requiring
    immediate medical attention. Monitoring these parameters together provides a more reliable
    indication of severe underlying conditions than monitoring them separately.*/
    private void checkHypotensiveHypoxemiaAlerts(String patientId, Map<String, Double> metrics) {
        if (metrics.containsKey("SystolicPressure") && metrics.containsKey("Saturation")) {
            double systolic = metrics.get("SystolicPressure");
            double saturation = metrics.get("Saturation");

            if (systolic < 90 && saturation < 92) {
                triggerAlert(new Alert(patientId, "Hypotensive Hypoxemia", System.currentTimeMillis()));
            }

        }

    }

    /*4. ECG Data Alerts
    Functionality Requirements:
    ● Abnormal Data: Trigger an alert if peaks above certain values happen. Measure the
    average data generated using a sliding window. Then if any peaks occur far beyond the
    current average generate an alert.*/
    private void checkECGDataAlerts(String patientId, Map<String, Double> metrics) {
        if (metrics.containsKey("ECG")) {

            List<Double> ecgReadings = dataStorage.getRecentReadings(patientId, "ECG", 5);
            double average = ecgReadings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            double latestReadings = metrics.get("ECG");
            if (latestReadings > average * 1.5) {
                triggerAlert(new Alert(patientId, "Abnormal ECG Data", System.currentTimeMillis()));
            }
        }
    }

    private boolean isTrend(List<Double> readings, double threshold) {
        for (int i = 1; i < readings.size(); i++) {
            if (Math.abs(readings.get(i) - readings.get(i - 1)) < threshold) {
                return false;
            }
        }
        return true;
    }

    private void triggerAlert(Alert alert) {
        AlertManager.getInstance().sendAlert(alert);
    }
}
