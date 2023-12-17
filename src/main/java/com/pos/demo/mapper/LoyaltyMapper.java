package com.pos.demo.mapper;

import com.pos.demo.model.dto.loyalty.CreateLoyaltyDto;
import com.pos.demo.model.dto.loyalty.LoyaltyDto;
import com.pos.demo.model.entity.LoyaltyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoyaltyMapper {
    LoyaltyDto createToDto (CreateLoyaltyDto loyaltyDto);
    LoyaltyDto entityToDTo (LoyaltyEntity loyaltyEntity);
}
