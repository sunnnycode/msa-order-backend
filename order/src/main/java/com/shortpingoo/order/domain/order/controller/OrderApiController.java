package com.shortpingoo.order.domain.order.controller;

import com.shortpingoo.order.domain.order.dto.OrderAllResponse;
import com.shortpingoo.order.domain.order.dto.OrderRequest;
import com.shortpingoo.order.domain.order.dto.OrderResponse;
import com.shortpingoo.order.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins="*")
public class OrderApiController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping("")
    public ResponseEntity<List<OrderResponse>> createOrder(
            @RequestHeader("X-User-Id") int userId,
            @RequestBody OrderRequest orderRequest
    ) {
        List<OrderResponse> orderResponse = orderService.createOrder(userId, orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

//    // 가게별 주문 전체 내역 조회
//    @GetMapping("/{storeId}")
//    public ResponseEntity<List<OrderResponse>> getOrderByStoreId(
//            @RequestHeader("X-User-Id") int userId,
//            @PathVariable("storeId") int storeId) {
//        OrderResponse orderResponse = orderService.getOrderByStoreId(storeId);
//        if (orderResponse != null) {
//            return ResponseEntity.ok(orderResponse);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    // 사용자(Client)의 본인 주문 전체 내역 조회
    @GetMapping("/client")
    public ResponseEntity<List<OrderAllResponse>> getOrderDetails(
            @RequestHeader("X-User-Id") int userId) {
        List<OrderAllResponse> orderAllResponse = orderService.getOrderDetails(userId);
        return ResponseEntity.ok(orderAllResponse);
    }




}
