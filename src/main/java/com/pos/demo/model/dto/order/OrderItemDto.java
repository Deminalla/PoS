package com.pos.demo.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private UUID orderItemId;
    private UUID orderId;
    private UUID itemId;
    private int quantity;
}
