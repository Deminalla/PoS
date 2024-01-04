package com.pos.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {
    private UUID reservationId;
    private UUID itemId;
    private UUID employeeId;
    private UUID customerId;
    private Date resStart;
    private Date resEnd;
    private boolean isReserved;
    private String description;
}