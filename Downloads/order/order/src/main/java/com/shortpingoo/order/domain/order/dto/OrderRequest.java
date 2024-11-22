package com.shortpingoo.order.domain.order.dto;


import com.shortpingoo.order.domain.orderitem.dto.OrderItemRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private int storeId;

    private List<OrderItemRequest> orderItemList;  // List<OrderItemRequest> 타입으로 정의
}


