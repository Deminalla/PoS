package com.pos.demo.repository;

import com.pos.demo.model.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Mapper
@Repository
public interface UserRepository {
    @Select("SELECT * FROM `user` WHERE user_id = #{user_id}")
    Optional<UserEntity> findById(@Param("user_id") UUID user_id);

    @Select("SELECT * FROM `user` WHERE email = #{email}")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Insert("INSERT INTO `user` (user_id, first_name, last_name, email, password, address, loyalty_points)" +
            "VALUES (#{userEntity.user_id}, #{userEntity.first_name}, #{userEntity.last_name}, " +
            "#{userEntity.email}, #{userEntity.password}, #{userEntity.address}, #{userEntity.loyalty_points})")
    int createUser(@Param("userEntity") UserEntity userEntity);

    @Delete("DELETE FROM `user` WHERE user_id = #{user_id}")
    int deleteUser(@Param("user_id") UUID user_id);

}
