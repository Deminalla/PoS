package com.pos.demo.model.dto.loyalty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoyaltyDto {
    private UUID loyaltyId;
    private UUID businessId;
    private String description;
    private int pointsRequired;
}