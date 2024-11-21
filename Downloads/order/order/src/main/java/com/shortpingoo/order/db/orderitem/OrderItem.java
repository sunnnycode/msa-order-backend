package com.shortpingoo.order.db.orderitem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_code")
    private Integer orderCode;

    @Column(name = "cart_item_code")
    private Integer cartItemCode;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "clothes_size", length = 20)
    private String clothesSize;

    @Column(name = "shoes_size", length = 20)
    private String shoesSize;

    @Column(name = "color", length = 20, nullable = false)
    private String color;

}
