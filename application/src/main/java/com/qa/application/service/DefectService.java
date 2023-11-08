package com.qa.application.service;

import com.qa.application.models.defect.Defect;

import java.util.List;

public interface DefectService {

    void addDefect(Integer id, Defect defect);
    List<Defect> findAllDefectsByInspectionId(Integer id);
    Defect updateDefect(Integer id, Defect newDefect);
    void deleteDefect(Integer id);
}
