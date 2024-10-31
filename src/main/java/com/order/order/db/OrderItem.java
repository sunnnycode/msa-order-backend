package com.order.order.db;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {
    @Id
    private int id;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "order_code")
    private Order order;

    // Constructors, Getters, and Setters
}
