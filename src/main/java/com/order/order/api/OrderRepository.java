package com.order.order.api;

import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public class OrderRepository {
    public interface OrderRepository extends JpaRepository<Order, Integer> {

    }
}
