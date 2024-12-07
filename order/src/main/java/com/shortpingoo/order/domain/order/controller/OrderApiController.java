package com.shortpingoo.order.domain.order.controller;

import com.shortpingoo.order.domain.order.dto.OrderAllResponse;
import com.shortpingoo.order.domain.order.dto.OrderRequest;
import com.shortpingoo.order.domain.order.dto.OrderResponse;
import com.shortpingoo.order.domain.order.dto.StatusRequest;
import com.shortpingoo.order.domain.order.service.OrderService;
import com.shortpingoo.order.domain.orderitem.dto.OrderItemResponse;
import com.siot.IamportRestClient.exception.IamportResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
//@CrossOrigin(origins="*")
public class OrderApiController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping("")
    public ResponseEntity<List<OrderResponse>> createOrder(
            @RequestHeader("X-User-Id") int userId,
            @RequestBody OrderRequest orderRequest
    ) throws IamportResponseException, IOException {
        String impUid = orderRequest.getImpUid();
        List<OrderResponse> orderResponse = orderService.createOrder(userId, orderRequest, impUid);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    // 가게별 주문 전체 내역 조회 (사용자 - owner)
    @GetMapping("/owner")
    public ResponseEntity<List<OrderAllResponse>> getOrdersByOwner(
            @RequestHeader("X-User-Id") int userId) {

        List<OrderAllResponse> orderAllResponses = orderService.getOrdersByOwner(userId);

        if (!orderAllResponses.isEmpty()) {
            return ResponseEntity.ok(orderAllResponses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // 사용자(Client)의 본인 주문 전체 내역 조회
    @GetMapping("/client")
    public ResponseEntity<List<OrderAllResponse>> getOrderDetails(
            @RequestHeader("X-User-Id") int userId) {
        List<OrderAllResponse> orderAllResponse = orderService.getOrderDetails(userId);
        return ResponseEntity.ok(orderAllResponse);
    }

    // 사용자(owner)의 주문 상태 변경
    @PatchMapping("/owner/{orderCode}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus (
            @PathVariable("orderCode") int orderCode,
            @RequestBody StatusRequest statusRequest,
            @RequestHeader("X-User-Id") int userId

    ) {
        OrderResponse orderResponse = orderService.updateOrderStatus(orderCode, userId, statusRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }




}
