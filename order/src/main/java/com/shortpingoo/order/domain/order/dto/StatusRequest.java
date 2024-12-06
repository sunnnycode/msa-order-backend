package com.shortpingoo.order.domain.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusRequest {

    @NotNull
    private int code; //orderCode

    @NotNull
    private int status;
}
