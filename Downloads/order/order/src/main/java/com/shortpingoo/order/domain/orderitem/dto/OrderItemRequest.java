package com.shortpingoo.order.domain.orderitem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    //private int cartItemId; //-> 여기서 productCode 가져옴

    //테스트용
    private int productCode;

    private int amount;

    private String color;

    private String clothesSize;

    private String shoesSize;
}
