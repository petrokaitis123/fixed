package com.qa.application.controllers;

import com.qa.application.controllers.payload.response.MessageResponse;
import com.qa.application.exceptions.product.ProductNotFoundException;
import com.qa.application.models.product.Product;
import com.qa.application.models.product.repository.ProductRepository;
import com.qa.application.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@EnableWebSecurity
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

UserDetailsImpl userDetails;
    //show all products
    @GetMapping("/products")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll()
                .forEach(p -> products.add(p));

        return products;
    }

    //show one product by id
    @GetMapping("/products/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Product getProductById(@PathVariable Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

    }

    //add product
    @PostMapping("/products")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<MessageResponse> addProduct(@RequestBody @Valid Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(new MessageResponse("Product have been successfully added!"));
    }

    //update product
    @PutMapping("/product/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable Integer id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }
    //delete product
    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    ResponseEntity<MessageResponse> deleteUser(@PathVariable Integer id) {
        if(!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("User with id " + id + " has been deleted successfuly"));
    }
}
