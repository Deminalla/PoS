package com.pos.demo.repository;

import com.pos.demo.model.dto.tax.TaxDto;
import com.pos.demo.model.entity.TaxEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface TaxRepository {
    @Select("SELECT * FROM tax WHERE tax_id = #{tax_id}")
    Optional<TaxEntity> findById(@Param("tax_id") UUID taxId);

    @Select("SELECT * FROM tax")
    List<TaxEntity> findAll();

    @Insert("INSERT INTO tax " +
            "(tax_id, amount_pct, description) " +
            "VALUES (#{tax.taxId}, #{tax.amountPct}, #{tax.description})")
    void createTax(@Param("tax") TaxEntity tax);

}
