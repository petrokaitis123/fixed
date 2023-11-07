package com.qa.application.exceptions.defect;


public class DefectNotFoundException  extends RuntimeException {

    public DefectNotFoundException(Integer id) {
        super("Could not find the product with id: " + id);
    }


}


