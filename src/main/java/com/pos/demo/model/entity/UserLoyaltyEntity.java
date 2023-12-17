package com.pos.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoyaltyEntity {
    private UUID userLoyaltyId;
    private UUID loyaltyId;
    private UUID userId;
    private int pointsAcquired;
}
