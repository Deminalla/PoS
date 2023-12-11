package com.pos.demo.controller;

import com.pos.demo.model.dto.UserDto;
import com.pos.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{user_id}")
    ResponseEntity<UserDto> getUserById(@PathVariable UUID user_id) {
        return ResponseEntity.ok(userService.getUserByID(user_id));
    }

    @PostMapping()
    ResponseEntity<UserDto> createUser(@RequestBody UserDto newUser) {
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    @DeleteMapping("/{user_id}")
    ResponseEntity<String> deleteUser(@PathVariable UUID user_id)
    {
        userService.deleteUser(user_id);
        //return ResponseEntity.ok();
        return ResponseEntity.ok("User deleted successfully");
    }
}
