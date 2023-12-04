package com.pos.demo.mapper;

import com.pos.demo.model.dto.ItemDto;
import com.pos.demo.model.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(target = "out_of_stock", source = "out_of_stock")
    ItemDto entityToDto(ItemEntity itemEntity);

    default boolean byteToBoolean(byte value) {
        return value != 0;
    }
}