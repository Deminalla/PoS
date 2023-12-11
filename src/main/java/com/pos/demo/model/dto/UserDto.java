package com.pos.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String address;
    private int loyalty_points;
}