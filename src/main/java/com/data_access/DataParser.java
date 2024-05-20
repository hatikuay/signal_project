package com.data_access;

import com.data_management.PatientRecord;

public interface DataParser {

    PatientRecord parse(String rawData);
}
