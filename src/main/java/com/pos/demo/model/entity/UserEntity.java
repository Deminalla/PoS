package com.pos.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// removed loyalty_points because it made sense to store it in a different table
// separate table specifically to keep track of points
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
}
