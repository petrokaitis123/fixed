package com.qa.application.exceptions.inspection;

public class InspectionNotFoundException  extends RuntimeException {

    public InspectionNotFoundException(Integer id) {
        super("Could not find the inspection with id: " + id);
    }


}
