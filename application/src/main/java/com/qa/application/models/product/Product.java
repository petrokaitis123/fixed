package com.qa.application.models.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qa.application.models.inspection.Inspection;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

import java.util.ArrayList;

/*
Product will have custom Id based on product, its name, description.
Product will be able to have multiple inspections @OneToMany relationship
*
* */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Digits(integer = 10, fraction = 0)
    private Integer productNumber;
    @NotBlank()
    private String name;
    @NotBlank()
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inspection> inspections = new ArrayList<>();


    public Product() {}

    public Product(Integer productNumber, String name, String description) {
        this.productNumber = productNumber;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
    }
}
