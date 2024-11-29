package com.shortpingoo.order.domain.order.service;

import com.shortpingoo.order.db.order.Order;
import com.shortpingoo.order.db.order.OrderRepository;
import com.shortpingoo.order.db.orderitem.OrderItem;
import com.shortpingoo.order.db.orderitem.OrderItemRepository;
import com.shortpingoo.order.domain.order.dto.OrderAllResponse;
import com.shortpingoo.order.domain.order.dto.OrderRequest;
import com.shortpingoo.order.domain.order.dto.OrderResponse;
import com.shortpingoo.order.domain.orderitem.dto.OrderItemRequest;
import com.shortpingoo.order.domain.orderitem.dto.OrderItemResponse;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceimpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceimpl(OrderRepository orderRepository, ModelMapper modelMapper, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.orderItemRepository = orderItemRepository;
    }

    // 주문 생성
    @Transactional
    @Override
    public List<OrderResponse> createOrder(int userId, OrderRequest orderRequest) {
        // 주문 데이터 저장
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setUserId(userId);
        order.setStatus(0); // 초기 상태 (예: PENDING)
        order.setOrderDate(LocalDateTime.now()); // 주문 날짜 설정

        // Order 저장
        orderRepository.save(order);

        // OrderItem 저장 및 응답 생성
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (OrderItemRequest itemRequest : orderRequest.getOrderItemList()) {
            OrderItem orderItem = modelMapper.map(itemRequest, OrderItem.class);
            orderItem.setOrder(order); // Order와 연관 설정
            orderItemRepository.save(orderItem);

            // OrderItem 기반 OrderResponse 생성
            OrderResponse orderResponse = modelMapper.map(orderItem, OrderResponse.class);
            orderResponse.setCode(order.getCode());
            //orderResponse.setStoreId(storeId);
            orderResponse.setUserId(userId);
            orderResponse.setStatus(order.getStatus());

            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

//     //가게별 주문 전체 내역 조회
//    @Override
//    public List<OrderResponse> getOrderByStoreId(int storeId, int userId) {
//        List<OrderItem> orderItems = orderItemRepository.findByStoreId(storeId);
//
//        // 상품이 없으면 빈 리스트 반환
//        if (products.isEmpty()) {
//            return Collections.emptyList();
//
//    }

    // 사용자(Client)의 본인 주문 전체 내역 조회
    @Override
    public List<OrderAllResponse> getOrderDetails(int userId) {
        // 여러 주문을 userId로 조회
        List<Order> orders = orderRepository.findByUserId(userId);

        // 주문 아이템을 조회하고 OrderItemResponse로 변환
        return orders.stream().map(order -> {
            List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
            List<OrderItemResponse> orderItemResponses = orderItems.stream()
                    .map(orderItem -> modelMapper.map(orderItem, OrderItemResponse.class))
                    .collect(Collectors.toList());

            return OrderAllResponse.builder()
                    .code(order.getCode())
                    .userId(order.getUserId())
                    .status(order.getStatus())
                    .orderDate(order.getOrderDate())
                    .orderItems(orderItemResponses)
                    .build();
        }).collect(Collectors.toList());
    }






}


