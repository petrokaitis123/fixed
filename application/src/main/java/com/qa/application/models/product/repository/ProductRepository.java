package com.qa.application.models.product.repository;

import com.qa.application.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findProductById(Integer id);
}
