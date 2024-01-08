package com.pos.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private UUID orderId;
    private UUID customerId;
    private String creationDate;
    private String pendingUntil;
    private String paidDateTime;
    private float gratuity;
    private String orderStatus;
    private UUID loyaltyProgramId;
    private UUID taxRateId;
}
