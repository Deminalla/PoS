package com.pos.demo.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


//For getting user info (password omitted)
// removed loyalty_points because it made sense to store it in a different table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}