package com.data_access;

import com.identification.PatientRecord;

public interface DataParser {

    PatientRecord parse(String rawData);
}
