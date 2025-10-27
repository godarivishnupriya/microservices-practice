package com.vishnu.microservicespractice.orderservice.service.impl;

import com.vishnu.microservicespractice.orderservice.client.ProductServiceClient;
import com.vishnu.microservicespractice.orderservice.config.RestClientConfig;
import com.vishnu.microservicespractice.orderservice.config.RestTemplateConfig;
import com.vishnu.microservicespractice.orderservice.constants.ServiceUrlConstants;
import com.vishnu.microservicespractice.orderservice.entity.Order;
import com.vishnu.microservicespractice.orderservice.model.Product;
import com.vishnu.microservicespractice.orderservice.repository.OrderRepository;
import com.vishnu.microservicespractice.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final RestTemplateConfig restTemplate;
    private final OrderRepository orderRepository;
    private final RestClientConfig restClient;
    private final ProductServiceClient productServiceClient;

    @Autowired
    public OrderServiceImpl(RestTemplateConfig restTemplate,
                            OrderRepository orderRepository,
                            RestClientConfig restClient,
                            ProductServiceClient productServiceClient) {
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
        this.restClient = restClient;
        this.productServiceClient = productServiceClient;
    }

    @Override
    public Order createOrderUsingRestTemplate(Long productId) {
        String url = ServiceUrlConstants.PRODUCT_SERVICE_BASE_URL + productId;
        Product product = restTemplate.getRestTemplateInstance().getForObject(url, Product.class);

        return getOrder(productId, product);
    }

    @Override
    public Order createOrderUsingRestClient(Long productId) {
        String url = ServiceUrlConstants.PRODUCT_SERVICE_BASE_URL + productId;
        Product product = restClient.getRestClientInstance().get()
                .uri(url)
                .retrieve()
                .body(Product.class);

        return getOrder(productId, product);
    }

    // Helper to create the order based on the product.
    private Order getOrder(Long productId, Product product) {
        if (product == null) throw new IllegalArgumentException("Product not found");

        Order order = new Order();
        order.setProductId(productId);
        order.setProductName(product.getName());
        order.setAmount(product.getPrice());

        return orderRepository.save(order);
    }

    @Override
    public Order createOrderUsingFeign(Long productId) {
        Product product = productServiceClient.getProductById(productId);

        Order order = new Order();
        order.setProductId(productId);
        order.setProductName(product.getName());
        order.setAmount(product.getPrice());

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existing = getOrderById(orderId);
        existing.setProductName(updatedOrder.getProductName());
        existing.setAmount(updatedOrder.getAmount());
        return orderRepository.save(existing);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
