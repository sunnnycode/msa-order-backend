package com.shortpingoo.order.domain.order.service;

import com.shortpingoo.order.db.order.Order;
import com.shortpingoo.order.db.order.OrderRepository;
import com.shortpingoo.order.db.orderitem.OrderItem;
import com.shortpingoo.order.db.orderitem.OrderItemRepository;
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
import java.util.List;

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

    // 가게별 주문 전체 내역 조회
//    @Override
//    public List<OrderResponse> getOrderByStoreId(int storeId, int userId) {
//
//    }

//    // 주문 건 별 상세 내역 조회
//    @Override
//    public OrderResponse getOrderDetails(int orderCode, int userId) {
//        // 주문을 orderCode와 userId로 조회
//        Order order = orderRepository.findByCodeAndUserId(orderCode, userId)
//                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
//
//        // 주문 아이템 조회
//        List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
//
//        // 주문 아이템을 OrderItemResponse로 변환
//        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
//        for (OrderItem orderItem : orderItems) {
//            OrderItemResponse orderItemResponse = modelMapper.map(orderItem, OrderItemResponse.class);
//            orderItemResponses.add(orderItemResponse);
//        }
//
//        // OrderResponse 생성
//        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
//        orderResponse.setOrderItems(orderItemResponses);
//
//        return orderResponse;
//    }


}


