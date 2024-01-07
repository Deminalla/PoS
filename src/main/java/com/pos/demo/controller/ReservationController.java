package com.pos.demo.controller;

import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.dto.reservation.CreateReservationDto;
import com.pos.demo.model.dto.reservation.ReservationDto;
import com.pos.demo.service.ReservationService;
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

    @GetMapping
    ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/employee/{employeeId}")
    ResponseEntity<List<ReservationDto>> getFreeReservationsByEmployee(@PathVariable UUID employeeId) {
        return ResponseEntity.ok(reservationService.getFreeReservationsByEmployee(employeeId));
    }

    @GetMapping("/item/{itemId}")
    ResponseEntity<List<ReservationDto>> getFreeReservationByItem(@PathVariable UUID itemId) {
        return ResponseEntity.ok(reservationService.getFreeReservationByItem(itemId));
    }

    @PostMapping
    ResponseEntity<ReservationDto> createReservation(@RequestBody CreateReservationDto reservationDto){
        return ResponseEntity.ok(reservationService.createReservation(reservationDto));
    }

    @PutMapping("/{reservationId}/{customerId}/reserve")
    ResponseEntity<ReservationDto> bookReservation(@PathVariable UUID reservationId, @PathVariable UUID customerId) {
        return ResponseEntity.ok(reservationService.bookReservation(reservationId, customerId));
    }

    @PutMapping("/{reservationId}/cancel")
    ResponseEntity<ReservationDto> cancelReservation(@PathVariable UUID reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }
}
