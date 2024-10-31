package com.order.order.service;
import com.order.order.api.OrderItemRepository;
import com.order.order.api.OrderRepository;
import com.order.order.db.Order;
import com.order.order.db.OrderItem;
import com.order.order.dto.OrderItemDto;
import com.order.order.dto.OrderRequestDto;
import com.order.order.dto.OrderResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
// service/OrderService.java
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setCode(orderRequestDto.getCode());
        order.setStatus("NEW");

        List<OrderItem> items = orderRequestDto.getItems().stream()
                .map(dto -> {
                    OrderItem item = new OrderItem();
                    item.setAmount(dto.getAmount());
                    item.setOrder(order);
                    return item;
                }).collect(Collectors.toList());

        order.setOrderItems(items);
        orderRepository.save(order);

        return new OrderResponseDto(order.getCode(), order.getStatus(), orderRequestDto.getItems());
    }

    public OrderResponseDto getOrder(int code) {
        Order order = orderRepository.findById(code).orElseThrow(() -> new RuntimeException("Order not found"));
        List<OrderItemDto> items = order.getOrderItems().stream()
                .map(item -> new OrderItemDto(item.getId(), item.getAmount()))
                .collect(Collectors.toList());

        return new OrderResponseDto(order.getCode(), order.getStatus(), items);
    }

    public void cancelOrder(int code) {
        Order order = orderRepository.findById(code).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus("CANCELED");
        orderRepository.save(order);
    }

    public OrderResponseDto updateOrder(int code, OrderRequestDto orderRequestDto) {
        Order order = orderRepository.findById(code).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus("UPDATED");

        List<OrderItem> items = orderRequestDto.getItems().stream()
                .map(dto -> {
                    OrderItem item = new OrderItem();
                    item.setAmount(dto.getAmount());
                    item.setOrder(order);
                    return item;
                }).collect(Collectors.toList());

        order.setOrderItems(items);
        orderRepository.save(order);

        return new OrderResponseDto(order.getCode(), order.getStatus(), orderRequestDto.getItems());
    }
}
