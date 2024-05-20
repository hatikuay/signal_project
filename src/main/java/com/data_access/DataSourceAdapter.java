package com.data_access;

import com.identification.PatientRecord;

public interface DataSourceAdapter {

    void storeData(PatientRecord data);

}
