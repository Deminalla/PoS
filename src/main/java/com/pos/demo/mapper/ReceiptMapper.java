package com.pos.demo.mapper;

import com.pos.demo.model.dto.receipt.CreateReceiptDto;
import com.pos.demo.model.dto.receipt.ReceiptDto;
import com.pos.demo.model.entity.ReceiptEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {
    ReceiptDto entityToDto(ReceiptEntity receiptEntity);
    ReceiptEntity dtoToEntity(ReceiptDto receiptDto);
    ReceiptDto createToDto(CreateReceiptDto createReceiptDto);
}
