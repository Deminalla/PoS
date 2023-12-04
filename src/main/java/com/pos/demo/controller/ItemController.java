package com.pos.demo.controller;

import com.pos.demo.model.dto.ItemDto;
import com.pos.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/{item_id}")
    ResponseEntity<ItemDto> getAchievementsByEmployeeId(@PathVariable BigInteger item_id) {
        log.info("Get data of specific item with ID {}", item_id);
        return ResponseEntity.ok(itemService.getItemByID(item_id));
    }

}
