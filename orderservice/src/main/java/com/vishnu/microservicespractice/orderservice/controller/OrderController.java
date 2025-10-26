package com.vishnu.microservicespractice.orderservice.controller;

import com.vishnu.microservicespractice.orderservice.entity.Order;
import com.vishnu.microservicespractice.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long productId) {
        return ResponseEntity.ok(orderService.createOrder(productId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long productId) {
        return ResponseEntity.ok(orderService.getOrderById(productId));
    }
}
