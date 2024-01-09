package com.pos.demo.repository;

import com.pos.demo.model.entity.OrderEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface OrderRepository {
    @Select("Select * FROM `order` WHERE order_id = #{orderId}")
    Optional<OrderEntity> findById(@Param("orderId") UUID orderId);

    @Insert("INSERT INTO `order` (order_id, customer_id, creation_date, pending_until, paid_date_time, gratuity, order_status, loyalty_program_id, tax_rate_id)" +
            "VALUES (#{orderEntity.orderId}, #{orderEntity.customerId}, #{orderEntity.creationDate}, #{orderEntity.pendingUntil}, #{orderEntity.paidDateTime}, " +
            "#{orderEntity.gratuity}, #{orderEntity.orderStatus}, #{orderEntity.loyaltyProgramId}, #{orderEntity.taxRateId})")
    int createOrder(@Param("orderEntity") OrderEntity orderEntity);

    @Update("UPDATE `order` SET order_id = #{orderEntity.orderId}, customer_id = #{orderEntity.customerId}, creation_date = #{orderEntity.creationDate}, " +
            "pending_until = #{orderEntity.pendingUntil}, paid_date_time = #{orderEntity.paidDateTime}, gratuity = #{orderEntity.gratuity}, order_status = #{orderEntity.orderStatus}, " +
            "loyalty_program_id = #{orderEntity.loyaltyProgramId}, tax_rate_id = #{orderEntity.taxRateId} WHERE order_id = #{orderEntity.orderId}")
    int updateOrder(@Param("orderEntity") OrderEntity orderEntity);

    @Update("UPDATE `order` SET " +
            "gratuity = #{orderGratuity} " +
            "WHERE order_id = #{orderId}")
    void updateOrderGratuity(@Param("orderId") UUID orderId, @Param("orderGratuity") float orderGratuity);

    @Update("UPDATE `order` SET " +
            "loyalty_program_id = #{loyaltyId} " +
            "WHERE order_id = #{orderId}")
    void updateOrderLoyalty(@Param("orderId") UUID orderId, @Param("loyaltyId") UUID loyaltyId);
}
