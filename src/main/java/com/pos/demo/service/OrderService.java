package com.pos.demo.service;

import com.pos.demo.mapper.OrderItemMapper;
import com.pos.demo.mapper.OrderMapper;
import com.pos.demo.model.dto.order.CreateOrderDto;
import com.pos.demo.model.dto.order.CreateOrderItemDto;
import com.pos.demo.model.dto.order.OrderDto;
import com.pos.demo.model.dto.order.OrderItemDto;
import com.pos.demo.model.entity.OrderEntity;
import com.pos.demo.model.entity.OrderItemEntity;
import com.pos.demo.repository.OrderItemRepository;
import com.pos.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    private static final String ORDER_NOT_FOUND_ERROR = "Order was not found";

    public OrderDto getOrderById(UUID orderId) {
        log.info("Searching for order with ID {}", orderId);
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);

        if (orderEntity.isEmpty()) {
            log.warn(ORDER_NOT_FOUND_ERROR);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND_ERROR);
        }

        return orderMapper.entityToDto(orderEntity.get());
    }

    public OrderDto createOrder( UUID customerId, CreateOrderDto createOrderDto) {
        log.info("Creating new order");

        OrderDto newOrderDto = orderMapper.createToDto(createOrderDto);
        UUID randomId = UUID.randomUUID();
        newOrderDto.setOrderId(randomId);
        newOrderDto.setCustomerId(customerId);

        OrderEntity orderEntity = orderMapper.dtoToEntity(newOrderDto);
        orderRepository.createOrder(orderEntity);

        OrderEntity newOrderEntity = orderRepository.findById(randomId).get();
        return orderMapper.entityToDto(newOrderEntity);
    }

    public OrderDto updateOrder(UUID orderId, CreateOrderDto updatedOrder) {
        log.info("Updating order with ID {}", orderId);

        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);

        if (orderEntity.isEmpty()) {
            log.warn(ORDER_NOT_FOUND_ERROR);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND_ERROR);
        }

        OrderDto orderDto = orderMapper.createToDto(updatedOrder);
        orderDto.setOrderId(orderId);
        orderRepository.updateOrder(orderMapper.dtoToEntity(orderDto));

        Optional<OrderEntity> foundOrderEntity = orderRepository.findById(orderId);

        return orderMapper.entityToDto(foundOrderEntity.get());
    }

    //TODO:
    // pay for order
    // provide receipt

    public void updateOrderGratuity(UUID orderId, float gratuity) {
        log.info("Updating order with ID {} gratuity to {}", orderId, gratuity);

        Optional<OrderEntity> order = orderRepository.findById(orderId);

        if(order.isEmpty()) {
            log.warn(ORDER_NOT_FOUND_ERROR);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND_ERROR);
        }

        orderRepository.updateOrderGratuity(orderId, gratuity);
    }

    public void updateOrderLoyalty(UUID orderId, UUID userLoyaltyId) {
        log.info("Updating order with ID {} loyalty to {}", orderId, userLoyaltyId);

        Optional<OrderEntity> order = orderRepository.findById(orderId);

        if(order.isEmpty()) {
            log.warn(ORDER_NOT_FOUND_ERROR);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND_ERROR);
        }

        orderRepository.updateOrderLoyalty(orderId, userLoyaltyId);
    }

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

    public OrderItemDto getOrderItemById(UUID orderItemId) {
        log.info("Searching for order item with ID {}", orderItemId);
        Optional<OrderItemEntity> orderItemEntity = orderItemRepository.findById(orderItemId);

        if (orderItemEntity.isEmpty()) {
            log.warn("order item was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item was not found");
        }

        return orderItemMapper.entityToDto(orderItemEntity.get());
    }
}
