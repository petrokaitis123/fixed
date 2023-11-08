package com.qa.application.service.impl;

import com.qa.application.exceptions.product.ProductNotFoundException;
import com.qa.application.models.product.Product;
import com.qa.application.models.product.repository.ProductRepository;
import com.qa.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {



    @Autowired
    ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(p ->
                products.add(p));
        return products;
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return product;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);

    }

    @Override
    public Product updateProduct(Integer id, Product newProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void deleteProduct(Integer id) {
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
