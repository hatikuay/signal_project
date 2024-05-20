package com.data_access;

import com.data_management.PatientRecord;

public interface DataSourceAdapter {

    void storeData(PatientRecord data);

}
