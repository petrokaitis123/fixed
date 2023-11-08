package com.qa.application.controllers;

import com.qa.application.controllers.payload.response.MessageResponse;
import com.qa.application.exceptions.inspection.InspectionNotFoundException;
import com.qa.application.exceptions.product.ProductNotFoundException;
import com.qa.application.models.inspection.Inspection;
import com.qa.application.models.inspection.repository.InspectionRepository;
import com.qa.application.models.product.Product;
import com.qa.application.models.product.repository.ProductRepository;
import com.qa.application.service.InspectionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api")
public class InspectionController {

    InspectionService inspectionService;


    @PostMapping("/inspections/add/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    void addInspection(@PathVariable Integer id, @RequestBody @Valid Inspection inspection) {
        inspectionService.addInspection(id,inspection);
    }

    @GetMapping("/inspections/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    List<Inspection> findAllInspections(@PathVariable Integer id){
        return inspectionService.findAllInspectionsByProductId(id);
    }

    @PutMapping("/inspections/update/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    void updateInspection(@PathVariable Integer id, @RequestBody Inspection inspection){
        inspectionService.updateInspection(id, inspection);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    void deleteInspection(@PathVariable Integer id) {
        inspectionService.deleteInspection(id);
    }


}