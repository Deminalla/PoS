package com.pos.demo.controller;

import com.pos.demo.model.dto.loyalty.CreateLoyaltyDto;
import com.pos.demo.model.dto.loyalty.LoyaltyDto;
import com.pos.demo.service.LoyaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/loyalty")
public class LoyaltyController {
    private final LoyaltyService loyaltyService;

    @PostMapping
    ResponseEntity<LoyaltyDto> createLoyaltyProgram(@RequestBody CreateLoyaltyDto loyaltyDto){
        // changed the return from string to the created object
        return new ResponseEntity<>(loyaltyService.createLoyalty(loyaltyDto), HttpStatus.CREATED);
    }

    @GetMapping("/{loyaltyId}")
    ResponseEntity<LoyaltyDto> getLoyaltyById(@PathVariable UUID loyaltyId){
        // this originally didn't return anything in swagger, just a success message :DD
        return ResponseEntity.ok(loyaltyService.findLoyalty(loyaltyId));
    }

    //TO DO

    // post /loyalty/discount
    // put /loyalty/discount/{discount_id}
    // get /loyalty/card/{card_code}
}
