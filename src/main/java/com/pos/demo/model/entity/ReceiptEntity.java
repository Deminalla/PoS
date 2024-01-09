package com.pos.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptEntity {
    private UUID receiptId;
    private UUID orderId;
    private UUID customerId;
    private UUID loyaltyId;
    private UUID taxId;
    private double price;
}
