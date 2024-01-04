package com.pos.demo.model.dto.tax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxDto {
    private UUID taxId;
    private Double amountPct;
    private String description;
}