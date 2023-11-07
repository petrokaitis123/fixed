package com.qa.application.controllers;

import com.qa.application.controllers.payload.response.MessageResponse;
import com.qa.application.exceptions.inspection.InspectionNotFoundException;
import com.qa.application.exceptions.product.ProductNotFoundException;
import com.qa.application.models.inspection.Inspection;
import com.qa.application.models.inspection.repository.InspectionRepository;
import com.qa.application.models.product.Product;
import com.qa.application.models.product.repository.ProductRepository;
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
public class InspectionController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    InspectionRepository inspectionRepository;


    @PostMapping("/inspections/add/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<MessageResponse> addInspection(@PathVariable Integer id, @RequestBody @Valid Inspection inspection) {
        Product product = productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        inspection.setProduct(product);
        inspectionRepository.save(inspection);
        return ResponseEntity.ok(new MessageResponse("Inspection have been successfully added."));
    }

    @GetMapping("/inspections/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<List<Inspection>> getInspectionsByProductId(@PathVariable Integer id) {
        if (!inspectionRepository.existsById(id)) {
            throw new InspectionNotFoundException(id);
        }
        return ResponseEntity.ok(inspectionRepository.findByProductId(id));
    }

}