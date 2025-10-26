package com.vishnu.microservicespractice.productservice.service.impl;

import com.vishnu.microservicespractice.productservice.entity.Product;
import com.vishnu.microservicespractice.productservice.repository.ProductRepository;
import com.vishnu.microservicespractice.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}

