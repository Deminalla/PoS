package com.pos.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private BigInteger user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String address;
    private int loyalty_points;
}
