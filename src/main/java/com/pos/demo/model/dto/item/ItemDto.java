package com.pos.demo.model.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private UUID itemId;
    private String name;
    private Double price;
    private String description;
    private UUID categoryId;
    private String type;
    private UUID businessId;
    private boolean outOfStock;
}