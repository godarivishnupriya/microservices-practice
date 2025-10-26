package com.vishnu.microservicespractice.productservice.service;

import com.vishnu.microservicespractice.productservice.entity.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(Long id);
}
