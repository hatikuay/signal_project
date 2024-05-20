package com.data_access;

import java.io.IOException;

import com.data_management.PatientRecord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataParser implements DataParser {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public PatientRecord parse(String rawData) {
    try {
      JsonNode jsonNode = objectMapper.readTree(rawData);
      int patientId = jsonNode.get("patientId").asInt();
      long timestamp = jsonNode.get("timestamp").asLong();
      String label = jsonNode.get("label").asText();
      double data = jsonNode.get("data").asDouble();
      return new PatientRecord(patientId, data, label, timestamp);
    } catch (IOException e) {
      throw new RuntimeException("Failed to parse JSON data", e);
    }
  }

}
