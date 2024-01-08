package com.pos.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {
    private UUID orderItemId;
    private UUID orderId;
    private UUID itemId;
    private int quantity;
}
