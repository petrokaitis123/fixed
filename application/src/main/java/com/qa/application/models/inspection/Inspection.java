package com.qa.application.models.inspection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qa.application.models.defect.Defect;
import com.qa.application.models.eRecords.EmployeeRecords;
import com.qa.application.models.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
* Inspections will have id assigned and incremented by one after each new inspection inserted.
* Also it will have when inspection was done, whos working on that inspection, and status which says if its ON GOING or COMPLETED.
* Inspections will have relationships with EmployeeRecord @ManyToMany bidirectional, that either inspection will be able to see who and how many were working on it as well as employeeRecords can track how many inspections particular employee made.
* Inspection will have @OneToMany unidirectional relationship, so inspection will be able to have multiple defect records available .
* Inspection will have @ManytoOne bidirectional relationship, so Product will be able to have multiple Inspections list.
* */
@Entity
@Table(name = "inspections")
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String inspectionDate;

    @NotBlank
    private String inspector;

    @NotNull
    private Boolean status;

    @JsonIgnore
    @ManyToMany(mappedBy = "inspections")
    private Set<EmployeeRecords> eRecords = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "inspection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Defect> defectList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Inspection() {}

    public Inspection(String inspectionDate, String inspector, Boolean status, Product product, Set<EmployeeRecords> eRecords,
                      List<Defect> defectList) {
        this.inspectionDate = inspectionDate;
        this.inspector = inspector;
        this.status = status;
        this.product = product;
        this.eRecords = eRecords;
        this.defectList = defectList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<EmployeeRecords> geteRecords() {
        return eRecords;
    }

    public void seteRecords(Set<EmployeeRecords> eRecords) {
        this.eRecords = eRecords;
    }

    public List<Defect> getDefectList() {
        return defectList;
    }

    public void setDefectList(List<Defect> defectList) {
        this.defectList = defectList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

