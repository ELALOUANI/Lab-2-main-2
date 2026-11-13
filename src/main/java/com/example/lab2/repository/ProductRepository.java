package com.example.lab2.repository;

import com.example.lab2.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    boolean deleteById(Long id);
}
