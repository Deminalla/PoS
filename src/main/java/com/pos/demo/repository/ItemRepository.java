package com.pos.demo.repository;

import com.pos.demo.model.dto.item.ItemDto;
import com.pos.demo.model.entity.ItemEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
public interface ItemRepository {
    @Select("SELECT * FROM item WHERE item_id = #{item_id}")
    Optional<ItemEntity> findById(@Param("item_id") UUID itemId);

    @Select("SELECT * FROM item")
    List<ItemEntity> findAll();

    @Insert("INSERT INTO item " +
            "(name, price, description, type, item_id, business_id, category_id, out_of_stock) " +
            "VALUES (#{item.name}, #{item.price}, #{item.description}, #{item.type}, #{item.itemId}," +
            "#{item.businessId}, #{item.categoryId}, #{item.outOfStock})")
    void createItem(@Param("item") ItemDto item);

    @Update("UPDATE item SET " +
            "name = #{item.name}, price = #{item.price}, " +
            "description = #{item.description}, " +
            "type = #{item.type}, " +
            "business_id = #{item.businessId}, " +
            "category_id = #{item.categoryId}, " +
            "out_of_stock = #{item.outOfStock} " +
            "WHERE item_id = #{item.itemId}")
    void updateItem(@Param("item") ItemDto item);

    @Delete("DELETE FROM item WHERE item_id=(#{itemId})")
    void deleteItem(@Param("itemId") UUID itemId);
}
