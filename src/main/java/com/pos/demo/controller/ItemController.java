package com.pos.demo.controller;

import com.pos.demo.model.dto.ItemDto;
import com.pos.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/{item_id}")
    ResponseEntity<ItemDto> getItemsById(@PathVariable BigInteger item_id) {
        return ResponseEntity.ok(itemService.getItemByID(item_id));
    }

    @GetMapping
    ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }
}
