package com.pos.demo.model.dto.employee;

import com.pos.demo.model.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private UserDto user;
    private UUID employeeId;
    private String employeeSince;
    private String jobTitle;
}
