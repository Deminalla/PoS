package com.pos.demo.controller;

import com.pos.demo.model.dto.loyalty.UserLoyaltyDto;
import com.pos.demo.service.UserLoyaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user-loyalty")
public class UserLoyaltyController {
    private final UserLoyaltyService userLoyaltyService;

    @GetMapping("/{userId}/{loyaltyId}")
    ResponseEntity<Integer> getUserLoyaltyPointsById(@PathVariable UUID userId, @PathVariable UUID loyaltyId) {
        return ResponseEntity.ok(userLoyaltyService.getUserLoyaltyPointsById(userId, loyaltyId));
    }

    @PutMapping("/{userId}/{loyaltyId}")
    ResponseEntity<String> updateUserLoyaltyPoints(@PathVariable UUID userId, @PathVariable UUID loyaltyId, Integer loyaltyPoints) {
        userLoyaltyService.updateUserLoyaltyPoints(userId, loyaltyId, loyaltyPoints);
        return ResponseEntity.ok("Loyalty points updated successfully.");
    }

    @GetMapping("/{userId}/loyalty-programs")
    ResponseEntity<List<UUID>> getAllThisUserLoyaltyPrograms(@PathVariable UUID userId) {
        return ResponseEntity.ok(userLoyaltyService.getAllThisUserLoyaltyPrograms(userId));
    }

    @PostMapping("/{userId}/{loyaltyId}")
    ResponseEntity<UserLoyaltyDto> addUserToLoyaltyProgram(@PathVariable UUID userId, @PathVariable UUID loyaltyId) {
        return ResponseEntity.ok(userLoyaltyService.addUserToLoyalty(userId, loyaltyId));
    }
}
