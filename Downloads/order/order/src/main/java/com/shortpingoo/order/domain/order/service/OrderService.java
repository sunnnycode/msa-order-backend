package com.shortpingoo.order.domain.order.service;

import com.shortpingoo.order.domain.order.dto.OrderRequest;
import com.shortpingoo.order.domain.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> createOrder(int storeId, int userId, OrderRequest orderRequest);

}
