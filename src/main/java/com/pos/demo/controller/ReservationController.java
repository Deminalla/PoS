package com.pos.demo.controller;

import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.dto.reservation.ReservationDto;
import com.pos.demo.model.dto.tax.CreateTaxDto;
import com.pos.demo.model.dto.tax.TaxDto;
import com.pos.demo.service.ReservationService;
import com.pos.demo.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/{reservationId}")
    ResponseEntity<ReservationDto> getReservationById(@PathVariable UUID reservationId) {
        return ResponseEntity.ok(reservationService.getReservationByID(reservationId));
    }

}
