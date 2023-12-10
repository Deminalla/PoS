package com.pos.demo.repository;

import com.pos.demo.model.entity.ItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
