package com.pos.demo.mapper;

import com.pos.demo.model.dto.item.CreateItemDto;
import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.dto.item.UpdateItem;
import com.pos.demo.model.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(target = "outOfStock", source = "outOfStock")
    ItemDto entityToDto(ItemEntity itemEntity);

    default boolean byteToBoolean(byte value) {
        return value != 0;
    }

    List<ItemDto> entityListToDto (List<ItemEntity> itemEntities);

    ItemDto createToDto(CreateItemDto item);

    ItemDto updateToDto(UpdateItem item);
}