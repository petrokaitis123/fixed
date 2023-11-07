package com.qa.application.models.defect.repository;

import com.qa.application.models.defect.Defect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefectRepository extends JpaRepository<Defect,Integer> {
    List<Defect> findByInspectionId(Integer id);
}
