package com.qa.application.service;

import com.qa.application.models.eRecords.EmployeeRecords;

public interface ERecordsService {

    EmployeeRecords findEmployeeRecords(Integer id);

    void addERecords(EmployeeRecords employeeRecords);
    EmployeeRecords updateEmployeeRecords(Integer id, EmployeeRecords newERecords);

}
