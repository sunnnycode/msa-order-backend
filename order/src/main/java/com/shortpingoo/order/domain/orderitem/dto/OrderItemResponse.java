package com.shortpingoo.order.domain.orderitem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderItemResponse {

    private int id;

    private int orderCode;

    private int productCode;

    private int amount;

    private String color;

    private String clothesSize;

    private String shoesSize;

    private String name;

    private String thumbnail;

    private int price;

}
