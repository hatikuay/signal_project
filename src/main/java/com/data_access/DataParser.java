package com.data_access;

import com.data_management.PatientData;

public interface DataParser {

    PatientData parse(String rawData);
}
