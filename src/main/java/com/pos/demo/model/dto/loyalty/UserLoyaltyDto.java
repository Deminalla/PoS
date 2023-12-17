package com.pos.demo.model.dto.loyalty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// separate table specifically to keep track of points
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoyaltyDto {
    private UUID userLoyaltyId;
    private UUID loyaltyId;
    private UUID userId;
    private int pointsAcquired;
}
