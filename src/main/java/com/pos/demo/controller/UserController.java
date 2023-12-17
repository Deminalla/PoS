package com.pos.demo.controller;

import com.pos.demo.model.dto.user.CreateUserDto;
import com.pos.demo.model.dto.user.UserInfoDto;
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

    @GetMapping("/{userId}")
    ResponseEntity<UserInfoDto> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUserByID(userId));
    }

    @PostMapping()
    ResponseEntity<UserInfoDto> createUser(@RequestBody CreateUserDto newUser) {
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/{userId}")
    ResponseEntity<UserInfoDto> updateUser(@PathVariable UUID userId, @RequestBody CreateUserDto updatedUser) {
        return ResponseEntity.ok(userService.updateUser(userId, updatedUser));
    }

    // clients were added to user endpoints because client is basically the same as user
    // TO DO
    //@GetMapping("/{userId}/orders")
}
