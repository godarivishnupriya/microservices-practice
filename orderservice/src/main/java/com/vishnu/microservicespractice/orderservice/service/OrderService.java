package com.vishnu.microservicespractice.orderservice.service;

import com.vishnu.microservicespractice.orderservice.entity.Order;

public interface OrderService {
    Order createOrder(Long productId);
    Order getOrderById(Long orderId);
}
