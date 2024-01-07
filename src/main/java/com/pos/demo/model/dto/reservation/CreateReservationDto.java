package com.pos.demo.model.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationDto {
    private UUID itemId;
    private UUID employeeId;
    private Date resStart;
    private Date resEnd;
    private String description;
}