package com.qa.application.controllers;

import com.qa.application.controllers.payload.response.MessageResponse;
import com.qa.application.exceptions.product.ProductNotFoundException;
import com.qa.application.models.product.Product;
import com.qa.application.models.product.repository.ProductRepository;
import com.qa.application.security.services.UserDetailsImpl;
import com.qa.application.service.ProductService;
import com.qa.application.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/products")
public class ProductController {

    ProductService productService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Product> getAll() {
       return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);


    }


    @PostMapping("/products")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public void addProduct(@RequestBody @Valid Product product) {
       productService.addProduct(product);
    }


    @PutMapping("/product/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public Product updateProduct(@RequestBody Product newProduct, @PathVariable Integer id) {
       return productService.updateProduct(id,newProduct);
    }
    //delete product
    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void deleteUser(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

}
