package com.pos.demo.service;

import com.pos.demo.mapper.OrderItemMapper;
import com.pos.demo.model.dto.order.CreateOrderItemDto;
import com.pos.demo.model.dto.order.OrderItemDto;
import com.pos.demo.model.entity.OrderItemEntity;
import com.pos.demo.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemDto createOrderItem(UUID orderId, CreateOrderItemDto createOrderItemDto) {
        log.info("Creating order item");

        OrderItemDto newOrderItem = orderItemMapper.createToDto(createOrderItemDto);
        UUID randomId = UUID.randomUUID();
        newOrderItem.setOrderItemId(randomId);
        newOrderItem.setOrderId(orderId);

        OrderItemEntity orderItemEntity = orderItemMapper.dtoToEntity(newOrderItem);
        orderItemRepository.createOrderItem(orderItemEntity);

        OrderItemEntity newOrderEntity = orderItemRepository.findById(randomId).get();
        return orderItemMapper.entityToDto(newOrderEntity);
    }
}
