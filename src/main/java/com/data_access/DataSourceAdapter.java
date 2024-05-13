package com.data_access;

import com.data_management.PatientData;

public interface DataSourceAdapter {

    void storeData(PatientData data);

}
