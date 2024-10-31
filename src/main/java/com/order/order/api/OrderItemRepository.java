package com.order.order.api;

import com.order.order.db.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public class OrderItemRepository {
    public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    }
}
