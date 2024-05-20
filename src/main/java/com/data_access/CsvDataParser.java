package com.data_access;


import com.identification.PatientRecord;

public class CsvDataParser implements DataParser {

  @Override
  public PatientRecord parse(String rawData) {
    try {
      String[] values = rawData.split(",");
      if (values.length != 4) {
        throw new IllegalArgumentException("Invalid CSV format. Expected 4 fields.");
      }
      int patientId = Integer.parseInt(values[0].trim());
      long timestamp = Long.parseLong(values[1].trim());
      String label = values[2].trim();
      double data = Double.parseDouble(values[3].trim());
      return new PatientRecord(patientId, data, label, timestamp);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CSV data", e);
    }
  }

}
