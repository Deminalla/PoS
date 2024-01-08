package com.pos.demo.mapper;

import com.pos.demo.model.dto.order.CreateOrderItemDto;
import com.pos.demo.model.dto.order.OrderItemDto;
import com.pos.demo.model.entity.OrderItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDto entityToDto(OrderItemEntity orderItemEntity);
    OrderItemEntity dtoToEntity(OrderItemDto orderItemDto);
    OrderItemDto createToDto(CreateOrderItemDto createOrderItemDto);
}
