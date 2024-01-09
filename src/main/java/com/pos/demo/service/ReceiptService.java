package com.pos.demo.service;

import com.pos.demo.mapper.ReceiptMapper;
import com.pos.demo.model.dto.receipt.CreateReceiptDto;
import com.pos.demo.model.dto.receipt.ReceiptDto;
import com.pos.demo.model.entity.OrderEntity;
import com.pos.demo.model.entity.OrderItemEntity;
import com.pos.demo.model.entity.ReceiptEntity;
import com.pos.demo.model.entity.TaxEntity;
import com.pos.demo.repository.*;
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
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;
    private final OrderRepository orderRepository;
    private final TaxRepository taxRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;
    private final UserLoyaltyRepository userLoyaltyRepository;
    private final LoyaltyRepository loyaltyRepository;

    public ReceiptDto getReceipt(UUID receiptId) {
        log.info("Searching for receipt with ID {}", receiptId);
        Optional<ReceiptEntity> receiptEntity = receiptRepository.findById(receiptId);

        if (receiptEntity.isEmpty()) {
            log.warn("Receipt was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receipt was not found");
        }

        return receiptMapper.entityToDto(receiptEntity.get());
    }

    public ReceiptDto createReceipt(UUID orderId) {
        log.info("Creating receipt for order with ID {}", orderId);

        //Setting random receipt ID and order ID from the used the order
        ReceiptDto newReceiptDto = receiptMapper.createToDto(new CreateReceiptDto());
        UUID randomId = UUID.randomUUID();
        newReceiptDto.setReceiptId(randomId);
        newReceiptDto.setOrderId(orderId);

        //Setting customer and tax ID
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        newReceiptDto.setCustomerId(orderEntity.getCustomerId());
        newReceiptDto.setTaxId(orderEntity.getTaxRateId());

        //Calculating total cost of the ordered items
        double totalCost = 0;

        List<OrderItemEntity> orderedItems = orderItemRepository.findAllItemsByOrder(orderId);

        for (OrderItemEntity item: orderedItems) {
            double itemPrice = itemRepository.findById(item.getItemId())
                    .get()
                    .getPrice();

            itemPrice *= item.getQuantity();
            totalCost += itemPrice;
        }

        //Increasing cost by the applied tax percentage
        TaxEntity taxEntity = taxRepository.findById(orderEntity.getTaxRateId()).get();
        totalCost = totalCost * (1 + (taxEntity.getAmountPct() / 100));

        //Applying user's loyalty to the order's total price and reducing it by the appropriate amount
        UUID userLoyalty = orderEntity.getLoyaltyProgramId();

        if (userLoyalty != null) {
            UUID loyaltyId = userLoyaltyRepository.findLoyaltyIdByUserLoyaltyId(userLoyalty).get();

            newReceiptDto.setLoyaltyId(loyaltyId);

            int pointsRequired = loyaltyRepository.findById(loyaltyId).get().getPointsRequired();

            switch (pointsRequired) {
                case 100:
                    totalCost *= 0.75;
                    break;
                case 150:
                    if (totalCost <= 25) {
                        totalCost = 0;
                    }
                    break;
            }
        }

        //Adding gratuity on top of the total calculated price
        newReceiptDto.setPrice(orderEntity.getGratuity() + totalCost);

        ReceiptEntity receiptEntity = receiptMapper.dtoToEntity(newReceiptDto);
        receiptRepository.createReceipt(receiptEntity);

        ReceiptEntity newReceiptEntity = receiptRepository.findById(randomId).get();
        return receiptMapper.entityToDto(newReceiptEntity);
    }
}
