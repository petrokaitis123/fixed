package com.qa.application.service;

import com.qa.application.models.product.Product;
import com.qa.application.models.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Integer id);

    void addProduct(Product product);

    Product updateProduct(Integer id, Product newProduct);

    void deleteProduct(Integer id);




}
