package com.pos.demo.mapper;

import com.pos.demo.model.dto.tax.CreateTaxDto;
import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.dto.tax.TaxDto;
import com.pos.demo.model.entity.TaxEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaxMapper {
    TaxDto entityToDto(TaxEntity taxEntity);

    TaxEntity dtoToEntity(TaxDto taxEntity);

    List<TaxDto> entityListToDto (List<TaxEntity> taxEntities);

    TaxDto createToDto(CreateTaxDto item);
}