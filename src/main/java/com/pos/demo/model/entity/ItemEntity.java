package com.pos.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {
    // all the id fields were changed to uuid to be more secure and have more variations
    private UUID itemId; // changed from int to UUID
    private String name;
    private Double price;
    private String description;
    private UUID categoryId; // changed from in to UUID
    private String type;
    private UUID businessId; // changed from string to UUID
    private byte outOfStock;
}
