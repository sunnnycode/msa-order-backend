package com.shortpingoo.order.domain.order.dto;

import com.shortpingoo.order.domain.orderitem.dto.OrderItemResponse;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderResponse {

    private int code; //orderCode

    private int userId;

    private int status;

    private LocalDateTime orderDate;


}
