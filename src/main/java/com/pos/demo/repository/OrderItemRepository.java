package com.pos.demo.repository;

import com.pos.demo.model.entity.OrderEntity;
import com.pos.demo.model.entity.OrderItemEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface OrderItemRepository {
    @Select("SELECT * FROM order_item WHERE order_item_id = #{orderItemId}")
    Optional<OrderItemEntity> findById(@Param("orderItemId") UUID orderItemId);

    @Select("SELECT * FROM order_item WHERE order_id = #{orderId}")
    List<OrderItemEntity> findAllItemsByOrder(@Param("orderId") UUID orderId);

    @Insert("Insert INTO order_item (order_item_id, order_id, item_id, quantity) " +
            "VALUES (#{orderItemEntity.orderItemId}, #{orderItemEntity.orderId}, #{orderItemEntity.itemId}, #{orderItemEntity.quantity})")
    int createOrderItem(@Param("orderItemEntity") OrderItemEntity orderItemEntity);
}
