package com.shortpingoo.order.domain.orderitem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockUpdateRequest {
    private int stock;
}
