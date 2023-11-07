package com.qa.application.models.inspection.repository;

import com.qa.application.models.inspection.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InspectionRepository extends JpaRepository<Inspection,Integer> {
    List<Inspection> findByProductId(Integer productId);
}
