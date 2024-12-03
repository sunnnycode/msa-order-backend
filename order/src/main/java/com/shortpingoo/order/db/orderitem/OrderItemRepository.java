package com.shortpingoo.order.db.orderitem;

import com.shortpingoo.order.db.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder(Order order);

    // Code를 기준으로 주문 항목을 찾는 메서드 (주문 내역을 가져오는 쿼리)
    List<OrderItem> findByProductCodeIn(List<String> codes);
}
