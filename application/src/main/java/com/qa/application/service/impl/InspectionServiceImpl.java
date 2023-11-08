package com.qa.application.service.impl;

import com.qa.application.exceptions.inspection.InspectionNotFoundException;
import com.qa.application.exceptions.product.ProductNotFoundException;
import com.qa.application.models.defect.Defect;
import com.qa.application.models.inspection.Inspection;
import com.qa.application.models.inspection.repository.InspectionRepository;
import com.qa.application.models.product.Product;
import com.qa.application.models.product.repository.ProductRepository;
import com.qa.application.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class InspectionServiceImpl implements InspectionService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    InspectionRepository inspectionRepository;
    @Override
    public void addInspection(@PathVariable Integer id, @RequestBody Inspection inspection) {
        Product product = productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        inspection.setProduct(product);
        inspectionRepository.save(inspection);


    }

    @Override
    public List<Inspection> findAllInspectionsByProductId(@PathVariable Integer id) {
        List<Inspection> inspections = new ArrayList<>();
        inspectionRepository.findAll().forEach(i ->
                inspections.add(i));
        return inspections;
    }

    @Override
    public Inspection updateInspection(@PathVariable Integer id, @RequestBody Inspection newInspection) {
        return inspectionRepository.findById(id).map(inspection -> {
            inspection.setInspectionDate(newInspection.getInspectionDate());
            inspection.setInspector(newInspection.getInspector());
            inspection.setStatus(newInspection.getStatus());
            return inspectionRepository.save(inspection);
        }).orElseThrow(() -> new InspectionNotFoundException(id));

    }

    @Override
    public void deleteInspection(@PathVariable Integer id) {
        if(!inspectionRepository.existsById(id)){
            throw new InspectionNotFoundException(id);
        }
        inspectionRepository.deleteById(id);
    }
}
