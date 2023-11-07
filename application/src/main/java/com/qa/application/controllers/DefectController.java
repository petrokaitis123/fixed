package com.qa.application.controllers;

import com.qa.application.controllers.payload.response.MessageResponse;
import com.qa.application.exceptions.defect.DefectNotFoundException;
import com.qa.application.models.defect.Defect;
import com.qa.application.models.defect.repository.DefectRepository;
import com.qa.application.models.inspection.Inspection;
import com.qa.application.models.inspection.repository.InspectionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@EnableWebSecurity
@RequestMapping("/api")
public class DefectController {


    @Autowired
    InspectionRepository inspectionRepository;
    @Autowired
    DefectRepository defectRepository;

    @PostMapping("/defects/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<MessageResponse> addDefect(@PathVariable Integer id, @RequestBody @Valid Defect defect) {
        Inspection inspection = inspectionRepository.findById(id)
                .orElseThrow(() -> new DefectNotFoundException(id));
        defect.setInspection(inspection);
        defectRepository.save(defect);
        return ResponseEntity.ok(new MessageResponse("Defect have been successfuly added."));
    }
    @GetMapping("/defects/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    List<Defect> getDefectsByInspectionId(@PathVariable Integer id){
        return defectRepository.findByInspectionId(id);
    }

}

