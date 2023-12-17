package com.pos.demo.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//For getting user data from api creation (id omitted)
// removed loyalty_points because it made sense to store it in a different table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
}