package com.pos.demo.controller;

import com.pos.demo.model.dto.order.CreateOrderDto;
import com.pos.demo.model.dto.order.CreateOrderItemDto;
import com.pos.demo.model.dto.order.OrderDto;
import com.pos.demo.model.dto.order.OrderItemDto;
import com.pos.demo.model.dto.receipt.ReceiptDto;
import com.pos.demo.service.OrderItemService;
import com.pos.demo.service.OrderService;
import com.pos.demo.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final ReceiptService receiptService;

    @GetMapping("/{orderId}")
    ResponseEntity<OrderDto> getOrderById(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PostMapping("/{customerId}/place-order")
    ResponseEntity<OrderDto> createOrder(@PathVariable UUID customerId, @RequestBody CreateOrderDto createOrderDto) {
        return ResponseEntity.ok(orderService.createOrder(customerId, createOrderDto));
    }

    @PutMapping("/{orderId}")
    ResponseEntity<OrderDto> updateOrder(@PathVariable UUID orderId, @RequestBody CreateOrderDto createOrderDto) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, createOrderDto));
    }

    @PutMapping("/{orderId}/gratuity")
    ResponseEntity<String> updateOrderGratuity(@PathVariable UUID orderId, @RequestParam float gratuity) {
        orderService.updateOrderGratuity(orderId, gratuity);
        return ResponseEntity.ok("Order gratuity updated successfully");
    }

    @PutMapping("/{orderId}/apply-loyalty")
    ResponseEntity<String> updateOrderLoyalty(@PathVariable UUID orderId, @RequestParam UUID userLoyaltyId) {
       orderService.updateOrderLoyalty(orderId, userLoyaltyId);
       return ResponseEntity.ok("Order loyalty applied successfully");
    }

    @PostMapping("/{orderId}/order-additional-items")
    ResponseEntity<OrderItemDto> createOrderItem(@PathVariable UUID orderId, @RequestBody CreateOrderItemDto createOrderItemDto) {
        return ResponseEntity.ok(orderItemService.createOrderItem(orderId, createOrderItemDto));
    }

    @GetMapping("/{receiptId}/print-receipt")
    ResponseEntity<ReceiptDto> getReceiptById(@PathVariable UUID receiptId) {
        return ResponseEntity.ok(receiptService.getReceipt(receiptId));
    }

    @PostMapping("/{orderId}/generate-receipt")
    ResponseEntity<ReceiptDto> createReceipt(@PathVariable UUID orderId) {
        return ResponseEntity.ok(receiptService.createReceipt(orderId));
    }
}
