package com.vishnu.microservicespractice.orderservice.client;

import com.vishnu.microservicespractice.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign client to communicate with product-service
@FeignClient(
        name = "product-service",
        url = "http://localhost:8081/api/products"
)
public interface ProductServiceClient {

    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
