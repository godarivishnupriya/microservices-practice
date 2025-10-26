package com.vishnu.microservicespractice.productservice.repository;

import com.vishnu.microservicespractice.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

