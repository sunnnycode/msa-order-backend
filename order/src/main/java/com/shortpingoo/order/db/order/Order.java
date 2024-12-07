package com.shortpingoo.order.db.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private int code; // orderCode

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "store_id", nullable = false)
    private int storeId;

    @Column(name = "imp_uid", nullable = false)
    private String impUid;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;


}
