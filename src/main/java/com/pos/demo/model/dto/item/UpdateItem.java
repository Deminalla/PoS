package com.pos.demo.model.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItem {
    private String name;
    private Double price;
    private String description;
    private UUID categoryId;
    private String type;
    private UUID businessId;
    private boolean outOfStock; // added this, makes sense to be able to change this
}