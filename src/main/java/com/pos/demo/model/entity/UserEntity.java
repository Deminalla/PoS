package com.pos.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private int loyaltyPoints;
}
