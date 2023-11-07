package com.qa.application.models.defect;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qa.application.models.inspection.Inspection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/*
* Defect will have id which will be auto incremented and assigned after each insert, description, severity of defect.
* It will have @ManytoOne bidirectional relationship, so multipleDefects will be assigned to Inspections
*
* */
@Entity
@Table(name = "defects")
public class Defect {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String description;

    @NotBlank
    private String severity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "inspection_id")
    private Inspection inspection;

    public Defect(){}

    public Defect(String description, String severity, Inspection inspection) {
        this.description = description;
        this.severity = severity;
        this.inspection = inspection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }
}
