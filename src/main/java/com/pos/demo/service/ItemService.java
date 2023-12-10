package com.pos.demo.service;

import com.pos.demo.mapper.ItemMapper;
import com.pos.demo.model.dto.ItemDto;
import com.pos.demo.model.entity.ItemEntity;
import com.pos.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemDto getItemByID(UUID itemId) {
        log.info("Searching for item with ID {}", itemId);
        Optional<ItemEntity> itemEntity = itemRepository.findById(itemId);

        if (itemEntity.isEmpty()) {
            log.warn("Item was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item was not found");
        }

        return itemMapper.entityToDto(itemEntity.get());
    }

    public List<ItemDto> getAllItems() {
        log.info("Fetching all items");
        List<ItemEntity> itemEntities = itemRepository.findAll();
        return itemMapper.entityListToDto(itemEntities);
    }
}
