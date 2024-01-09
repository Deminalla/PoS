package com.pos.demo.repository;

import com.pos.demo.model.entity.ReceiptEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface ReceiptRepository {
    @Select("SELECT * FROM receipt WHERE receipt_id = #{receiptId}")
    Optional<ReceiptEntity> findById(@Param("receiptId")UUID receiptId);

    @Insert("INSERT INTO receipt (receipt_id, order_id, customer_id, loyalty_id, tax_id, price)" +
            "VALUES (#{receiptEntity.receiptId}, #{receiptEntity.orderId}, #{receiptEntity.customerId}, " +
            "#{receiptEntity.loyaltyId}, #{receiptEntity.taxId}, #{receiptEntity.price})")
    int createReceipt(@Param("receiptEntity") ReceiptEntity receiptEntity);
}
