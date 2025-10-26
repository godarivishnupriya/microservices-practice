package com.vishnu.microservicespractice.orderservice.repository;

import com.vishnu.microservicespractice.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

