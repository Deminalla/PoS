package com.pos.demo.mapper;

import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.dto.loyalty.CreateLoyaltyDto;
import com.pos.demo.model.dto.loyalty.LoyaltyDto;
import com.pos.demo.model.dto.reservation.CreateReservationDto;
import com.pos.demo.model.dto.reservation.ReservationDto;
import com.pos.demo.model.entity.ItemEntity;
import com.pos.demo.model.entity.ReservationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationDto entityToDto(ReservationEntity reservationEntity);

    ReservationDto createToDto (CreateReservationDto reservationDto);

    List<ReservationDto> entityListToDto (List<ReservationEntity> reservationEntities);

    ReservationEntity dtoToEntity (ReservationDto reservationDto);
}