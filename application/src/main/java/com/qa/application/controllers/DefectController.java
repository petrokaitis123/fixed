package com.qa.application.controllers;

import com.qa.application.controllers.payload.response.MessageResponse;
import com.qa.application.exceptions.defect.DefectNotFoundException;
import com.qa.application.models.defect.Defect;
import com.qa.application.models.defect.repository.DefectRepository;
import com.qa.application.models.inspection.Inspection;
import com.qa.application.models.inspection.repository.InspectionRepository;
import com.qa.application.service.DefectService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefectController {

    DefectService defectService;

    @PostMapping("/defects/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void addDefect(@PathVariable Integer id, @RequestBody @Valid Defect defect) {
        defectService.addDefect(id,defect);

    }
    @GetMapping("/defects/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    List<Defect> getDefectsByInspectionId(@PathVariable Integer id) {
        return defectService.findAllDefectsByInspectionId(id);
    }
    @PutMapping("/defects/update/{id}")
    @PreAuthorize("or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void updateDefect(@PathVariable Integer id, @RequestBody Defect defect) {
    }

    @DeleteMapping("/defects/delete/{id}")
    @PreAuthorize("or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void deleteDefect(@PathVariable Integer id){
        defectService.deleteDefect(id);
    }
}

