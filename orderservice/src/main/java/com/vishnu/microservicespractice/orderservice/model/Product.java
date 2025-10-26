package com.vishnu.microservicespractice.orderservice.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
}

