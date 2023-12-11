package com.pos.demo.mapper;

import com.pos.demo.model.dto.TaxDto;
import com.pos.demo.model.entity.TaxEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaxMapper {
    TaxDto entityToDto(TaxEntity taxEntity);
}