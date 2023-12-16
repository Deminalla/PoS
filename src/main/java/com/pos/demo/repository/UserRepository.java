package com.pos.demo.repository;

import com.pos.demo.model.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Mapper
@Repository
public interface UserRepository {
    @Select("SELECT * FROM `user` WHERE user_id = #{userId}")
    Optional<UserEntity> findById(@Param("userId") UUID userId);

    @Select("SELECT * FROM `user` WHERE email = #{email}")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Insert("INSERT INTO `user` (user_id, first_name, last_name, email, password, address, loyalty_points)" +
            "VALUES (#{userEntity.userId}, #{userEntity.firstName}, #{userEntity.lastName}, " +
            "#{userEntity.email}, #{userEntity.password}, #{userEntity.address}, #{userEntity.loyaltyPoints})")
    int createUser(@Param("userEntity") UserEntity userEntity);

    @Delete("DELETE FROM `user` WHERE user_id = #{userId}")
    int deleteUser(@Param("userId") UUID userId);

    @Update("UPDATE `user` SET first_name = #{userEntity.firstName}, last_name = #{userEntity.lastName}, " +
            "email = #{userEntity.email}, password = #{userEntity.password}, " +
            "address = #{userEntity.address}, loyalty_points = #{userEntity.loyaltyPoints} " +
            "WHERE user_id = #{userEntity.userId}")
    int updateUser(@Param("userEntity") UserEntity userEntity);


}
