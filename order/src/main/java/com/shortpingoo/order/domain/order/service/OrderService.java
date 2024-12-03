package com.shortpingoo.order.domain.order.service;

import com.shortpingoo.order.domain.order.dto.OrderAllResponse;
import com.shortpingoo.order.domain.order.dto.OrderRequest;
import com.shortpingoo.order.domain.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> createOrder(int userId, OrderRequest orderRequest);
    List<OrderAllResponse> getOrdersByOwner(int userId);
    List<OrderAllResponse> getOrderDetails(int userId);

}
