package com.pos.demo.service;

import com.pos.demo.mapper.ItemMapper;
import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.dto.item.CreateItemDto;
import com.pos.demo.model.dto.item.UpdateItem;
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

    public ItemDto createItem(CreateItemDto itemDto) {
        log.info("Creating item {}", itemDto);

        ItemDto item = itemMapper.createToDto(itemDto);
        UUID randomId = UUID.randomUUID();
        item.setItemId(randomId);
        itemRepository.createItem(item);
        ItemEntity itemEntity = itemRepository.findById(randomId).get();
        return itemMapper.entityToDto(itemEntity);
    }

    public ItemDto updateItemByID(UUID itemId, UpdateItem item) {
        log.info("Searching for item with ID {}", itemId);
        Optional<ItemEntity> itemEntity = itemRepository.findById(itemId);

        if (itemEntity.isEmpty()) {
            log.warn("Item was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item was not found");
        }

        log.info("Updating item details");

        ItemDto itemDto = itemMapper.updateToDto(item);
        itemDto.setItemId(itemId);
        itemRepository.updateItem(itemDto);

        return itemDto;
    }

    public ItemDto deleteItemByID(UUID itemId){
        ItemDto item = getItemByID(itemId);
        itemRepository.deleteItem(itemId);
        log.info("Item {} was deleted", item);
        return item;
    }
}
