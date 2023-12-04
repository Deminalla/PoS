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

import java.math.BigInteger;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemDto getItemByID(BigInteger item_id) {

        log.info("Searching for item with ID {}", item_id);

        Optional<ItemEntity> itemEntity = itemRepository.findById(item_id);


        if (itemEntity.isEmpty()) {
            log.warn("Item was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item was not found");
        }

        return itemMapper.entityToDto(itemEntity.get());

    }
}
