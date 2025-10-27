package com.vishnu.microservicespractice.orderservice.service;

import com.vishnu.microservicespractice.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrderUsingRestTemplate(Long productId);
    Order createOrderUsingRestClient(Long productId);
    Order createOrderUsingFeign(Long productId);
    Order getOrderById(Long orderId);
    Order updateOrder(Long orderId, Order updatedOrder);
    void deleteOrder(Long orderId);
    List<Order> getAllOrders();
}
