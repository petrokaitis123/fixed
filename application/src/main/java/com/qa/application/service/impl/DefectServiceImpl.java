package com.qa.application.service.impl;

import com.qa.application.exceptions.defect.DefectNotFoundException;
import com.qa.application.models.defect.Defect;
import com.qa.application.models.defect.repository.DefectRepository;
import com.qa.application.models.inspection.Inspection;
import com.qa.application.models.inspection.repository.InspectionRepository;
import com.qa.application.service.DefectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefectServiceImpl implements DefectService {

    @Autowired
    InspectionRepository inspectionRepository;
    @Autowired
    DefectRepository defectRepository;

    @Override
    public void addDefect(Integer id, Defect defect) {
        Inspection inspection = inspectionRepository.findById(id)
                .orElseThrow(() -> new DefectNotFoundException(id));
        defect.setInspection(inspection);
        defectRepository.save(defect);
    }

    @Override
    public List<Defect> findAllDefectsByInspectionId(Integer id) {
        List<Defect> defects = new ArrayList<>();
        defectRepository.findByInspectionId(id).forEach(d ->
                defects.add(d));

        return defects;
    }

    @Override
    public Defect updateDefect(Integer id, Defect newDefect) {
        return defectRepository.findById(id).map(d -> {

            d.setDescription(newDefect.getDescription());
            d.setSeverity(newDefect.getSeverity());
            return defectRepository.save(d);
        }).orElseThrow(() -> new DefectNotFoundException(id));

    }

    @Override
    public void deleteDefect(Integer id) {
        if(!defectRepository.existsById(id)){
            throw new DefectNotFoundException(id);
        }
        defectRepository.deleteById(id);
    }
}
