package com.shortpingoo.order.db.orderitem;

import com.shortpingoo.order.db.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_code", nullable = false)
    private Order order;

    @Column(name = "product_code", nullable = false)
    private int productCode;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "clothes_size", length = 20)
    private String clothesSize;

    @Column(name = "shoes_size", length = 20)
    private String shoesSize;

    @Column(name = "color", length = 20, nullable = false)
    private String color;

    //productName
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @Column(name = "price", nullable = false)
    private int price;

}
