package com.shortpingoo.order.db.orderitem;

import com.shortpingoo.order.db.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder(Order order);
}
