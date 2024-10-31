package com.order.order.controller;
import com.order.order.dto.OrderRequestDto;
import com.order.order.dto.OrderResponseDto;
import com.order.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController  // Spring MVC의 RESTful 컨트롤러로 선언
@RequestMapping("/api/orders")  // "/api/orders" 경로로 들어오는 요청을 처리
public class OrderController {
    private final OrderService orderService;  // 주문 관련 비즈니스 로직을 처리할 서비스

    // OrderController 생성자, orderService를 주입받음
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문 하기
    @PostMapping("/{userId}")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto order = orderService.createOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    // 주문 조회
    @GetMapping("/{userId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable int code) {
        OrderResponseDto order = orderService.getOrder(code);
        return ResponseEntity.ok(order);
    }

    // 주문 취소
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable int code) {
        orderService.cancelOrder(code);
        return ResponseEntity.noContent().build();
    }

    // 주문 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable int code, @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto order = orderService.updateOrder(code, orderRequestDto);
        return ResponseEntity.ok(order);
    }

    //비동기 api 추후 구현
}
