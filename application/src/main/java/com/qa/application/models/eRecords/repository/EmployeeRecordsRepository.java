package com.qa.application.models.eRecords.repository;

import com.qa.application.models.eRecords.EmployeeRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRecordsRepository extends JpaRepository<EmployeeRecords,Integer> {

    EmployeeRecords findDetailsByUserId(Integer id);
}
