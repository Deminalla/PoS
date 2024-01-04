package com.pos.demo.mapper;

import com.pos.demo.model.dto.reservation.ReservationDto;
import com.pos.demo.model.entity.ReservationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationDto entityToDto(ReservationEntity reservationEntity);
}