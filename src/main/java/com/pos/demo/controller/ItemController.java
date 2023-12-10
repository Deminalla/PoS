package com.pos.demo.controller;

import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.dto.item.CreateItemDto;
import com.pos.demo.model.dto.item.UpdateItem;
import com.pos.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/{itemId}")
    ResponseEntity<ItemDto> getItemById(@PathVariable UUID itemId) {
        return ResponseEntity.ok(itemService.getItemByID(itemId));
    }

    @GetMapping
    ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @PostMapping
    ResponseEntity<ItemDto> createItem(@RequestBody CreateItemDto itemDto){
        return ResponseEntity.ok(itemService.createItem(itemDto));
    }

    @PutMapping("/{itemId}")
    ResponseEntity<ItemDto> UpdateItemById(@PathVariable UUID itemId, @RequestBody UpdateItem item) {
        return ResponseEntity.ok(itemService.updateItemByID(itemId, item));
    }
}
