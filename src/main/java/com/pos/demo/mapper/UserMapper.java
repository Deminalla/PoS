package com.pos.demo.mapper;

import com.pos.demo.model.dto.UserDto;
import com.pos.demo.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entityToDto(UserEntity userEntity);
    UserEntity dtoToEntity(UserDto userDto);
}