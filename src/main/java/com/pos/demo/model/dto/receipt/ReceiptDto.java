package com.pos.demo.model.dto.receipt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDto {
    private UUID receiptId;
    private UUID orderId;
    private UUID customerId;
    private UUID loyaltyId;
    private UUID taxId;
    private double price;
}
