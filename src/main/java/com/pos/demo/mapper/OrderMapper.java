package com.pos.demo.mapper;

import com.pos.demo.model.dto.order.CreateOrderDto;
import com.pos.demo.model.dto.order.OrderDto;
import com.pos.demo.model.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto entityToDto(OrderEntity orderEntity);
    OrderEntity dtoToEntity(OrderDto orderDto);
    OrderDto createToDto(CreateOrderDto createOrderDto);
}
