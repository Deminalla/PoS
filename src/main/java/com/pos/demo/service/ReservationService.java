package com.pos.demo.service;

import com.pos.demo.mapper.ReservationMapper;
import com.pos.demo.mapper.TaxMapper;
import com.pos.demo.model.dto.item.CreateItemDto;
import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.dto.item.UpdateItem;
import com.pos.demo.model.dto.reservation.CreateReservationDto;
import com.pos.demo.model.dto.reservation.ReservationDto;
import com.pos.demo.model.dto.tax.CreateTaxDto;
import com.pos.demo.model.dto.tax.TaxDto;
import com.pos.demo.model.dto.user.UserDto;
import com.pos.demo.model.entity.ItemEntity;
import com.pos.demo.model.entity.ReservationEntity;
import com.pos.demo.model.entity.TaxEntity;
import com.pos.demo.model.entity.UserEntity;
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
        log.info("Searching for reservation with ID {}", reservationId);
        Optional<ReservationEntity> reservationEntity = reservationRepository.findById(reservationId);
        if (reservationEntity.isEmpty()) {
            log.warn("Reservation was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation was not found");
        }

        return reservationMapper.entityToDto(reservationEntity.get());
    }

    public List<ReservationDto> getAllReservations() {
        log.info("Fetching all reservations");
        List<ReservationEntity> reservationEntities = reservationRepository.findAll();
        return reservationMapper.entityListToDto(reservationEntities);
    }

    public List<ReservationDto> getFreeReservationsByEmployee(UUID employeeId) {
        log.info("Fetching free reservations tied to employee {}", employeeId);
        List<ReservationEntity> reservationEntities = reservationRepository.findFreeByEmployee(employeeId);
        return reservationMapper.entityListToDto(reservationEntities);
    }

    public List<ReservationDto> getFreeReservationByItem(UUID itemId) {
        log.info("Fetching free reservations tied to item {}", itemId);
        List<ReservationEntity> reservationEntities = reservationRepository.findFreeByItem(itemId);
        return reservationMapper.entityListToDto(reservationEntities);
    }


    public ReservationDto createReservation(CreateReservationDto reservationDto) {
        log.info("Creating reservation {}", reservationDto);

        ReservationDto reservation = reservationMapper.createToDto(reservationDto);

        UUID randomId = UUID.randomUUID();
        reservation.setReservationId(randomId);

        log.info(reservation);
        reservationRepository.createReservation(reservation);

        Optional<ReservationEntity> newReservationEntity = reservationRepository.findById(reservation.getReservationId());

        return reservationMapper.entityToDto(newReservationEntity.get());
    }

    public ReservationDto bookReservation(UUID reservationId, UUID customerId) {
        log.info("Booking reservation with ID {} for customer {}", reservationId, customerId);
        Optional<ReservationEntity> reservationEntity = reservationRepository.findById(reservationId);

        if (reservationEntity.isEmpty()) {
            log.warn("Reservation was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation was not found");
        }
        ReservationEntity reservation = reservationEntity.get();

        if(reservation.isReserved()) {
            log.warn("Reservation is already booked");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Reservation is already booked");
        }

        log.info("Booking reservation");

        reservation.setCustomerId(customerId);
        reservation.setReserved(true);
        reservationRepository.updateReservation(reservation);

        return reservationMapper.entityToDto(reservation);
    }

    public ReservationDto cancelReservation(UUID reservationId) {
        log.info("Canceling reservation with ID {}", reservationId);
        Optional<ReservationEntity> reservationEntity = reservationRepository.findById(reservationId);

        if (reservationEntity.isEmpty()) {
            log.warn("Reservation was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation was not found");
        }
        ReservationEntity reservation = reservationEntity.get();

        if(!reservation.isReserved()) {
            log.warn("Reservation was already available");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Reservation was already available");
        }

        reservation.setCustomerId(null);
        reservation.setReserved(false);
        reservationRepository.updateReservation(reservation);

        return reservationMapper.entityToDto(reservation);
    }

}
