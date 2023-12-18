package com.pos.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    private UUID userId;
    private UUID employeeId;
    private String employeeSince;
    private String jobTitle;
}
