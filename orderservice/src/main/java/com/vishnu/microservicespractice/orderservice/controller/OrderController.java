package com.vishnu.microservicespractice.orderservice.controller;

import com.vishnu.microservicespractice.orderservice.entity.Order;
import com.vishnu.microservicespractice.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create order using different clients
    @PostMapping("/resttemplate/{productId}")
    public ResponseEntity<Order> createOrderWithRestTemplate(@PathVariable Long productId) {
        return ResponseEntity.ok(orderService.createOrderUsingRestTemplate(productId));
    }

    @PostMapping("/restclient/{productId}")
    public ResponseEntity<Order> createOrderWithRestClient(@PathVariable Long productId) {
        return ResponseEntity.ok(orderService.createOrderUsingRestClient(productId));
    }

    @PostMapping("/feign/{productId}")
    public ResponseEntity<Order> createOrderWithFeign(@PathVariable Long productId) {
        return ResponseEntity.ok(orderService.createOrderUsingFeign(productId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return ResponseEntity.ok(orderService.updateOrder(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
