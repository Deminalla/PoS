package com.pos.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String name;
    private Double price;
    private String description;
    private String category_id;
    private String type;
    private BigInteger item_id;
    private String business_id;
    private boolean out_of_stock;

}