package com.pos.demo.repository;

import com.pos.demo.model.dto.loyalty.LoyaltyDto;
import com.pos.demo.model.entity.LoyaltyEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface LoyaltyRepository {
    @Insert("INSERT INTO loyalty " +
            "(loyalty_id, business_id, description, points_required) " +
            "VALUES (#{loyalty.loyaltyId}, #{loyalty.businessId}, #{loyalty.description}, #{loyalty.pointsRequired})")
    void createLoyalty(@Param("loyalty") LoyaltyDto loyalty);

    @Select("SELECT * FROM loyalty WHERE loyalty_id = #{loyalty_id}")
    Optional<LoyaltyEntity> findById(@Param("loyalty_id") UUID loyaltyId);
}
