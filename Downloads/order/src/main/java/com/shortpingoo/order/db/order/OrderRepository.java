package com.shortpingoo.order.db.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findByCodeAndUserId(int orderCode, int userId);
}
