package com.pos.demo.mapper;

import com.pos.demo.model.dto.employee.EmployeeCreateDto;
import com.pos.demo.model.dto.employee.EmployeeDto;
import com.pos.demo.model.dto.employee.EmployeeInfoDto;
import com.pos.demo.model.dto.user.UserInfoDto;
import com.pos.demo.model.entity.EmployeeEntity;
import com.pos.demo.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    @Autowired
    protected UserService userService;

    @Mapping(target = "userId", source = "user.userId")
    public abstract EmployeeEntity dtoToEntity(EmployeeDto employeeDto);

    @Mapping(target = "user", source = "userId")
    public abstract EmployeeInfoDto entityToDto(EmployeeEntity employeeEntity);

    UserInfoDto map(UUID userId) {
        return userService.getUserByID(userId);
    }

    public abstract EmployeeDto createToDto(EmployeeCreateDto employeeCreateDto);
}
