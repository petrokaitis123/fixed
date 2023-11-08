package com.qa.application.service.impl;

import com.qa.application.exceptions.eRecords.ERecordsNotFoundException;
import com.qa.application.models.eRecords.EmployeeRecords;
import com.qa.application.models.eRecords.repository.EmployeeRecordsRepository;
import com.qa.application.service.ERecordsService;
import org.springframework.beans.factory.annotation.Autowired;

public class ERecordsServiceImpl implements ERecordsService {

    @Autowired
    EmployeeRecordsRepository employeeRecordsRepository;

    @Override
    public EmployeeRecords findEmployeeRecords(Integer id) {
       EmployeeRecords eRecords = employeeRecordsRepository.findDetailsByUserId(id)
               .orElseThrow(() -> new ERecordsNotFoundException(id));
       return eRecords;
    }

    @Override
    public void addERecords(EmployeeRecords employeeRecords) {

        employeeRecordsRepository.save(employeeRecords);
    }

    @Override
    public EmployeeRecords updateEmployeeRecords(Integer id, EmployeeRecords newERecords) {
         return employeeRecordsRepository.findDetailsByUserId(id)
                 .map(employeeRecords -> {
                     employeeRecords.setFirstname(newERecords.getFirstname());
                     employeeRecords.setLastname(newERecords.getLastname());
                     employeeRecords.setAddress(newERecords.getAddress());
                     employeeRecords.setMobileNumber(newERecords.getMobileNumber());
                     employeeRecords.setOccupation(newERecords.getOccupation());

             return employeeRecordsRepository.save(employeeRecords);
         }).orElseThrow(() -> new ERecordsNotFoundException(id));
    }
}
