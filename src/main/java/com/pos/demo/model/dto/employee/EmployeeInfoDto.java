package com.pos.demo.model.dto.employee;

import com.pos.demo.model.dto.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoDto {
    private UserInfoDto user;
    private UUID employeeId;
    private String employeeSince;
    private String jobTitle;
}
