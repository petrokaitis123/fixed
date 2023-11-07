package com.qa.application.exceptions.product;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Integer id) {
        super("Could not find the product with id: " + id);
    }

}