package com.pos.demo.repository;

import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.dto.loyalty.UserLoyaltyDto;
import com.pos.demo.model.dto.reservation.ReservationDto;
import com.pos.demo.model.entity.ItemEntity;
import com.pos.demo.model.entity.ReservationEntity;
import com.pos.demo.model.entity.TaxEntity;
import com.pos.demo.model.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface ReservationRepository {
    @Select("SELECT * FROM reservation WHERE reservation_id = #{reservation_id}")
    Optional<ReservationEntity> findById(@Param("reservation_id") UUID reservationId);

    @Select("SELECT * FROM reservation")
    List<ReservationEntity> findAll();

    @Select("SELECT * FROM reservation WHERE is_reserved = false AND employee_id = #{employeeId}")
    List<ReservationEntity> findFreeByEmployee(@Param("employeeId") UUID employeeId);

    @Select("SELECT * FROM reservation WHERE is_reserved = false AND item_id = #{itemId}")
    List<ReservationEntity> findFreeByItem(@Param("itemId") UUID itemId);

    @Insert("INSERT INTO reservation " +
            "(reservation_id, item_id, employee_id, customer_id, res_start, res_end, is_reserved, description) " +
            "VALUES (#{reservation.reservationId}, #{reservation.itemId}, #{reservation.employeeId}, " +
            "#{reservation.customerId}, #{reservation.resStart}, #{reservation.resEnd}, " +
            "#{reservation.isReserved}, #{reservation.description})")
    void createReservation(@Param("reservation") ReservationDto reservation);

    @Update("UPDATE reservation SET " +
            "customer_id = #{reservation.customerId}, is_reserved = #{reservation.isReserved} " +
            "WHERE reservation_id = #{reservation.reservationId}")
    void updateReservation(@Param("reservation") ReservationEntity reservation);
}