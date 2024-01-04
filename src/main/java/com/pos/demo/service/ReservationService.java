package com.pos.demo.service;

import com.pos.demo.mapper.ReservationMapper;
import com.pos.demo.mapper.TaxMapper;
import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.dto.reservation.ReservationDto;
import com.pos.demo.model.dto.tax.CreateTaxDto;
import com.pos.demo.model.dto.tax.TaxDto;
import com.pos.demo.model.entity.ItemEntity;
import com.pos.demo.model.entity.ReservationEntity;
import com.pos.demo.model.entity.TaxEntity;
import com.pos.demo.repository.ReservationRepository;
import com.pos.demo.repository.TaxRepository;
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
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationDto getReservationByID(UUID reservationId) {
        log.info("Searching for item with ID {}", reservationId);
        Optional<ReservationEntity> reservationEntity = reservationRepository.findById(reservationId);
        if (reservationEntity.isEmpty()) {
            log.warn("Reservation was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation was not found");
        }

        return reservationMapper.entityToDto(reservationEntity.get());
    }

}
