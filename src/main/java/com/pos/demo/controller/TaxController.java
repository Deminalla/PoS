package com.pos.demo.controller;

import com.pos.demo.model.dto.TaxDto;
import com.pos.demo.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tax")
public class TaxController {
    private final TaxService taxService;

    @GetMapping("/{taxId}")
    ResponseEntity<TaxDto> getTaxById(@PathVariable UUID taxId) {
        return ResponseEntity.ok(taxService.getTaxByID(taxId));
    }
}
