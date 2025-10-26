package com.vishnu.microservicespractice.orderservice.service.impl;

import com.vishnu.microservicespractice.orderservice.config.RestClientConfig;
import com.vishnu.microservicespractice.orderservice.constants.ServiceUrlConstants;
import com.vishnu.microservicespractice.orderservice.entity.Order;
import com.vishnu.microservicespractice.orderservice.model.Product;
import com.vishnu.microservicespractice.orderservice.repository.OrderRepository;
import com.vishnu.microservicespractice.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;
    private final RestClientConfig  restClientConfig;

    @Autowired
    public OrderServiceImpl(RestTemplate restTemplate, OrderRepository orderRepository) {
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
        this.restClientConfig = new RestClientConfig();
    }

    @Override
    public Order createOrder(Long productId) {
        String url = ServiceUrlConstants.PRODUCT_SERVICE_BASE_URL + productId;
        //Product product = restTemplate.getForObject(url, Product.class);
        Product product = restClientConfig.getRestClientInstance()
                .get()
                .uri(url)
                .retrieve()
                .body(Product.class);

        if (product == null) {
            LOGGER.warn("Product not found for ID: {}", productId);
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }

        Order order = new Order();
        order.setProductName(product.getName());
        order.setAmount(product.getPrice());
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
