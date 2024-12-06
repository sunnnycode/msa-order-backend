package com.shortpingoo.order.db.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(int userId);
    Optional<Order> findByCode(int code);

}
