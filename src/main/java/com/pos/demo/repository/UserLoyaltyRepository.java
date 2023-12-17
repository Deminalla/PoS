package com.pos.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface UserLoyaltyRepository {
    @Select("SELECT points_acquired FROM user_loyalty WHERE user_id = #{user_id} AND loyalty_id = #{loyalty_id}")
    Optional<Integer> findPointsByUserAndLoyaltyId(@Param("user_id") UUID userId, @Param("loyalty_id") UUID loyaltyId);

    @Update("UPDATE user_loyalty SET " +
            "points_acquired = #{points} " +
            "WHERE user_id = #{user_id} AND loyalty_id = #{loyalty_id}")
    void updatePoints(@Param("user_id") UUID userId, @Param("loyalty_id") UUID loyaltyId, @Param("points") Integer points);

    @Select("SELECT loyalty_id FROM user_loyalty WHERE user_id = #{user_id}")
    List<UUID> getUserLoyaltyPrograms(@Param("user_id") UUID userId);
}
