package com.pos.demo.model.dto.employee;

import com.pos.demo.model.dto.user.CreateUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateDto {
    private CreateUserDto user;
    private String employeeSince;
    private String jobTitle;
}
