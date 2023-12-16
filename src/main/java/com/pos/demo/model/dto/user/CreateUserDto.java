package com.pos.demo.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


//For getting user data from api creation (id omitted)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private int loyaltyPoints;
}