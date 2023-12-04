package com.example.authreg.repo;

import com.example.authreg.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductModel, Long> {
    ProductModel findByName(String name);
}
