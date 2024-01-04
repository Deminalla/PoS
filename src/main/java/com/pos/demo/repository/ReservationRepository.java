package com.pos.demo.repository;

import com.pos.demo.model.entity.ItemEntity;
import com.pos.demo.model.entity.ReservationEntity;
import com.pos.demo.model.entity.TaxEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface ReservationRepository {
    @Select("SELECT * FROM reservation WHERE reservation_id = #{reservation_id}")
    Optional<ReservationEntity> findById(@Param("reservation_id") UUID reservationId);
}
