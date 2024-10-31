package com.order.order.db;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import org.springframework.data.annotation.Id;

@Entity
public class Order {
    @Id
    private int code;

    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    // Constructors, Getters, and Setters
}