package com.shortpingoo.order.domain.order.controller;

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

    @PostMapping("/{storeId}")
    public ResponseEntity<List<OrderResponse>> createOrder(
            @PathVariable int storeId,
            @RequestHeader("X-User-Id") int userId,
            @RequestBody OrderRequest orderRequest
    ) {
        List<OrderResponse> orderResponse = orderService.createOrder(storeId, userId, orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }


}
