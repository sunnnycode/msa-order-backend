package com.shortpingoo.order.domain.order.dto;


import com.shortpingoo.order.domain.orderitem.dto.OrderItemResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderAllResponse {

    private int code; //orderCode

    private int userId;

    private List<OrderItemResponse> orderItems;

    private int status;

    private LocalDateTime orderDate;


}
