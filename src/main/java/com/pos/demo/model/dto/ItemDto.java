package com.pos.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String name;
    private Double price;
    private String description;
    private UUID categoryId;
    private String type;
    private UUID itemId;
    private UUID businessId;
    private boolean outOfStock;

}