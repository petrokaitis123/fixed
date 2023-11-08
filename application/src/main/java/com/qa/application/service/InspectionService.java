package com.qa.application.service;

import com.qa.application.models.defect.Defect;
import com.qa.application.models.inspection.Inspection;

import java.util.List;

public interface InspectionService {

    void addInspection(Integer id, Inspection inspection);
    List<Inspection> findAllInspectionsByProductId(Integer id);
    Inspection updateInspection(Integer id, Inspection newInspection);
    void deleteInspection(Integer id);
}
