package com.shortpingoo.order.domain.order.service;

import com.shortpingoo.order.domain.order.dto.OrderAllResponse;
import com.shortpingoo.order.domain.order.dto.OrderRequest;
import com.shortpingoo.order.domain.order.dto.OrderResponse;
import com.shortpingoo.order.domain.order.dto.StatusRequest;
import com.siot.IamportRestClient.exception.IamportResponseException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface OrderService {

    List<OrderResponse> createOrder(int userId, OrderRequest orderRequest, String impUid)
            throws IamportResponseException, IOException;
    List<OrderAllResponse> getOrdersByOwner(int userId);
    List<OrderAllResponse> getOrderDetails(int userId);
    OrderResponse updateOrderStatus(int orderCode, int userId, StatusRequest statusRequest);

}
