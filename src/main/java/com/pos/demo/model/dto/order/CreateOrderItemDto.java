package com.pos.demo.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemDto {
    private UUID orderId;
    private UUID itemId;
    private int quantity;
}
