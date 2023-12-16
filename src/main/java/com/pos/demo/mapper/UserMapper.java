package com.pos.demo.mapper;

import com.pos.demo.model.dto.user.CreateUserDto;
import com.pos.demo.model.dto.user.UserDto;
import com.pos.demo.model.dto.user.UserInfoDto;
import com.pos.demo.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entityToDto(UserEntity userEntity);
    UserEntity dtoToEntity(UserDto userDto);
    UserDto createToDto(CreateUserDto createUserDto);
    //UserInfoDto entityToUserInfoDto(UserEntity userEntity);
    UserInfoDto userToInfo(UserDto userDto);
}